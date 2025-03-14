import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbaService } from '../../services/nba.service';

@Component({
  selector: 'app-today-games',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Today's Games</h2>
    <div *ngFor="let game of games" class="game-card">
    <p><strong>{{ game.visitor_team.full_name }}</strong> at <strong>{{ game.home_team.full_name }}</strong></p>
      <p><strong>{{ formatDate(game.datetime) }}</strong></p>

      <!-- Show game details only if it has started -->
      <ng-container *ngIf="game.period > 0">
        <p>Score: {{ game.home_team_score }} - {{ game.visitor_team_score }}</p>
        <p>Period: {{ game.period }}</p>
        <p *ngIf="game.time">Time Left: {{ game.time }}</p>
      </ng-container>
    </div>
  `,
  styleUrls: ['./today-games.component.scss']
})

export class TodayGamesComponent implements OnInit {
  games: any[] = [];

  constructor(private nbaService: NbaService) {}

  ngOnInit(): void {
    this.nbaService.getTodayGames().subscribe(data => {
      this.games = data;
      //console.log("Games data:", JSON.stringify(this.games, null, 2));
    });
  }

  formatDate(datetime: string): string {
    const date = new Date(datetime);
    return date.toLocaleString('en-US', {
      //weekday: 'long',
      //month: 'long',
      //day: 'numeric',
      //year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      hour12: true
    });
  }
}
