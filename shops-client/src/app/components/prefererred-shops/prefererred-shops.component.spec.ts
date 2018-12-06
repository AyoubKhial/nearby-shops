import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrefererredShopsComponent } from './prefererred-shops.component';

describe('PrefererredShopsComponent', () => {
  let component: PrefererredShopsComponent;
  let fixture: ComponentFixture<PrefererredShopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrefererredShopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrefererredShopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
