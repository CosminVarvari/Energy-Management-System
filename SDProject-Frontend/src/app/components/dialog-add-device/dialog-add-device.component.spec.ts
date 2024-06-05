import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddDeviceComponent } from './dialog-add-device.component';

describe('DialogAddDeviceComponent', () => {
  let component: DialogAddDeviceComponent;
  let fixture: ComponentFixture<DialogAddDeviceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogAddDeviceComponent]
    });
    fixture = TestBed.createComponent(DialogAddDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
