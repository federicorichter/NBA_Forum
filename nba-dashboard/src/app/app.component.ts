import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodayGamesComponent } from './components/today-games/today-games.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    TodayGamesComponent,
    TeamListComponent,
    MatSidenavModule,
    MatListModule,
    RouterOutlet
  ],
  template: `
    <mat-sidenav-container>
    <mat-sidenav mode="side" opened>
      <app-team-list></app-team-list>
    </mat-sidenav>
    <mat-sidenav-content>
      <router-outlet></router-outlet> <!-- This now controls all views -->
    </mat-sidenav-content>
  </mat-sidenav-container>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent {}
