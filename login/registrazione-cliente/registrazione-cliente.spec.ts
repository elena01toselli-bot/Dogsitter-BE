import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrazioneCliente } from './registrazione-cliente';

describe('RegistrazioneCliente', () => {
  let component: RegistrazioneCliente;
  let fixture: ComponentFixture<RegistrazioneCliente>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrazioneCliente],
    }).compileComponents();

    fixture = TestBed.createComponent(RegistrazioneCliente);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
