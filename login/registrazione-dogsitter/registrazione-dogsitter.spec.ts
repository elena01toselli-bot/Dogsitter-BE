import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrazioneDogsitter } from './registrazione-dogsitter';

describe('RegistrazioneDogsitter', () => {
  let component: RegistrazioneDogsitter;
  let fixture: ComponentFixture<RegistrazioneDogsitter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrazioneDogsitter],
    }).compileComponents();

    fixture = TestBed.createComponent(RegistrazioneDogsitter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
