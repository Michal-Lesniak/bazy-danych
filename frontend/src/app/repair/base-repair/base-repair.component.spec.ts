import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseRepairComponent } from './base-repair.component';

describe('BaseRepairComponent', () => {
  let component: BaseRepairComponent;
  let fixture: ComponentFixture<BaseRepairComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BaseRepairComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BaseRepairComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
