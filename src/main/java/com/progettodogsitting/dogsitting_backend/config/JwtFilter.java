package com.progettodogsitting.dogsitting_backend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component  // Spring lo registra automaticamente come filtro, niente SecurityConfig
public class JwtFilter implements Filter {  // Filter di Jakarta, NON OncePerRequestFilter

    // ⚠️ In produzione metti questa in application.properties, mai nel codice
    private static final String CHIAVE_SEGRETA = "la-tua-chiave-segreta-molto-lunga-almeno-32-caratteri";
    private static final long DURATA_TOKEN_MS = 86400000L; // 24 ore
    private final Key key;

    // Rotte che passano senza token - devono corrispondere ESATTAMENTE ai path del controller
    private static final List<String> ROTTE_PUBBLICHE = List.of(
        "/api/utenti/login",
        "/api/utenti/registra"
    );

    public JwtFilter() {
        this.key = Keys.hmacShaKeyFor(CHIAVE_SEGRETA.getBytes());
    }

    // ─── Usato dal controller per generare il token dopo il login ────────────

    public String generaToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + DURATA_TOKEN_MS))
            .signWith(key)
            .compact();
    }

    // ─── Usabile in futuro se vuoi sapere CHI ha fatto la richiesta ──────────

    public String estraiUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    // ─── Validazione interna ──────────────────────────────────────────────────

    private boolean isTokenValido(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // token scaduto, firma errata, o malformato
        }
    }

    // ─── Questo viene chiamato da Spring su OGNI richiesta HTTP ──────────────

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest)  req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI(); // es. "/api/utenti/login"

        // Rotta pubblica → lascia passare senza controllare nulla
        if (ROTTE_PUBBLICHE.stream().anyMatch(path::startsWith)) {
            chain.doFilter(req, res);
            return;
        }

        // Tutte le altre rotte richiedono il token
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token mancante");
            return; // Blocca qui, il controller non viene mai chiamato
        }

        String token = header.substring(7); // Taglia "Bearer " e prende solo il token

        if (!isTokenValido(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token non valido o scaduto");
            return;
        }

        // Token OK → passa al controller
        chain.doFilter(req, res);
    }
}