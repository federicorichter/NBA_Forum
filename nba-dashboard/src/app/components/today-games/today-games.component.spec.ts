import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodayGamesComponent } from './today-games.component';

describe('TodayGamesComponent', () => {
  let component: TodayGamesComponent;
  let fixture: ComponentFixture<TodayGamesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodayGamesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodayGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
