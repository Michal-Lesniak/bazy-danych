import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewApplianceComponent } from './new-appliance.component';

describe('NewApplianceComponent', () => {
  let component: NewApplianceComponent;
  let fixture: ComponentFixture<NewApplianceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewApplianceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewApplianceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
