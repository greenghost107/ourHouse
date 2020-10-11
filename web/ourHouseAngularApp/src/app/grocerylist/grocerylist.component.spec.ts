import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrocerylistComponent } from './grocerylist.component';

describe('GrocerylistComponent', () => {
  let component: GrocerylistComponent;
  let fixture: ComponentFixture<GrocerylistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GrocerylistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GrocerylistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
