import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDateActionComponent } from './add-date-action.component';

describe('AddDateActionComponent', () => {
  let component: AddDateActionComponent;
  let fixture: ComponentFixture<AddDateActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDateActionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDateActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
