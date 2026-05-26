import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrazioneCampo } from './registrazione-campo';

describe('RegistrazioneCampo', () => {
  let component: RegistrazioneCampo;
  let fixture: ComponentFixture<RegistrazioneCampo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrazioneCampo],
    }).compileComponents();

    fixture = TestBed.createComponent(RegistrazioneCampo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
