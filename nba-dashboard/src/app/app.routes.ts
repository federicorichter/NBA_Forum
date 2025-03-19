import { Routes } from '@angular/router';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';
import { AppComponent } from './app.component';
import { TodayGamesComponent } from './components/today-games/today-games.component';

export const routes: Routes = [
  { path: 'team/:id', component: TeamDetailComponent },
  { path: '', component: TodayGamesComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' } // Default fallback route
];
