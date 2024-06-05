import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogDeleteDeviceComponent } from './dialog-delete-device.component';

describe('DialogDeleteDeviceComponent', () => {
  let component: DialogDeleteDeviceComponent;
  let fixture: ComponentFixture<DialogDeleteDeviceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogDeleteDeviceComponent]
    });
    fixture = TestBed.createComponent(DialogDeleteDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
