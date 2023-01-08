import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDasboardComponent } from './user-dasboard.component';

describe('UserDasboardComponent', () => {
  let component: UserDasboardComponent;
  let fixture: ComponentFixture<UserDasboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDasboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserDasboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
