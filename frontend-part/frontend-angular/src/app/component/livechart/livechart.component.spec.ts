import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LivechartComponent } from './livechart.component';

describe('LivechartComponent', () => {
  let component: LivechartComponent;
  let fixture: ComponentFixture<LivechartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LivechartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LivechartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
