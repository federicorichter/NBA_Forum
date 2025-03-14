import { Routes } from '@angular/router';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';
import { AppComponent } from './app.component';

export const routes: Routes = [
  { path: 'team/:id', component: TeamDetailComponent },
  { path: '**', component: AppComponent } // Default fallback route
];
