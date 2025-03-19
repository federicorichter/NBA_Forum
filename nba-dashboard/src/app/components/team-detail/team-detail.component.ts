import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NbaService } from '../../services/nba.service';

@Component({
  selector: 'app-team-detail',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="team-detail" *ngIf="team">
      <h2>{{ team.full_name }}</h2>
      <p><strong>Conference:</strong> {{ team.conference }}</p>
      <p><strong>Division:</strong> {{ team.division }}</p>
      <p><strong>Abbreviation:</strong> {{ team.abbreviation }}</p>
    </div>
  `,
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {
  team: any = null;
  route = inject(ActivatedRoute);
  nbaService = inject(NbaService);

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const teamId = params['id'];  // Get team ID from URL
      if (teamId) {
        this.nbaService.getTeamById(teamId).subscribe(response => {
          console.log("API Response:", response);
          this.team = response.data;  // ✅ Ensure correct data path
        });
      }
    });
  }
}
