package com.progettodogsitting.dogsitting_backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progettodogsitting.dogsitting_backend.DTO.RegistrazioneDTO;
import com.progettodogsitting.dogsitting_backend.DTO.LoginRequest;
import com.progettodogsitting.dogsitting_backend.DTO.CaneDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Transactional // Rollback automatico del DB dopo ogni test
class ApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegistrazioneELoginDogsitter() throws Exception {
        // Registrazione Dogsitter
        RegistrazioneDTO reg = new RegistrazioneDTO();
        reg.setUsername("test.dogsitter");
        reg.setPassword("password123");
        reg.setRuolo("dogsitter");
        reg.setNomeBattesimo("Test");
        reg.setCognome("Dogsitter");
        reg.setMaxCani(3);
        reg.setTaglieCani(List.of("Media", "Grande"));
        reg.setGiorniDisponibili(List.of("LUN", "MAR"));

        mockMvc.perform(post("/api/utenti/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reg)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("test.dogsitter")))
                .andExpect(jsonPath("$.ruolo", is("dogsitter")))
                .andExpect(jsonPath("$.token").exists());

        // Login Dogsitter
        LoginRequest login = new LoginRequest();
        login.setUsername("test.dogsitter");
        login.setPassword("password123");
        login.setRuolo("dogsitter");

        mockMvc.perform(post("/api/utenti/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ruolo", is("dogsitter")))
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testCreazioneCane() throws Exception {
        // Creazione Utente Cliente
        RegistrazioneDTO reg = new RegistrazioneDTO();
        reg.setUsername("test.cliente");
        reg.setPassword("password123");
        reg.setRuolo("cliente");
        reg.setNomeBattesimo("Test");
        reg.setCognome("Cliente");

        mockMvc.perform(post("/api/utenti/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reg)))
                .andExpect(status().isOk());

        // Creazione Cane
        CaneDTO cane = new CaneDTO();
        cane.setNMicrochip("111111111111111");
        cane.setNome("FidoTest");
        cane.setRazza("Labrador");
        cane.setTaglia("Grande");
        cane.setDataNascita(LocalDate.of(2020, 5, 10));
        cane.setUsernameCliente("test.cliente");

        mockMvc.perform(post("/api/cani")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cane)))
                .andExpect(status().isNoContent());

        // Lettura Cani Cliente
        mockMvc.perform(get("/api/cani/cliente/test.cliente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("FidoTest")));
    }

    @Test
    void testGetListaDogsitter() throws Exception {
        mockMvc.perform(get("/api/dogsitter"))
                .andExpect(status().isOk());
    }
}
