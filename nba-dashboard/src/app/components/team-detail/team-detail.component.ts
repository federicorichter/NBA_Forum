import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NbaService } from '../../services/nba.service';

@Component({
  selector: 'app-team-detail',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngIf="team">
      <h2>{{ team.full_name }}</h2>
      <p><strong>Conference:</strong> {{ team.conference }}</p>
      <p><strong>Division:</strong> {{ team.division }}</p>
      <p><strong>Abbreviation:</strong> {{ team.abbreviation }}</p>
    </div>
  `,
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {
  team: any;

  constructor(private route: ActivatedRoute, private nbaService: NbaService) {}

  ngOnInit(): void {
    const teamId = this.route.snapshot.paramMap.get('id');
  
    this.nbaService.getTeamById(teamId).subscribe(data => {
      this.team = data;
    });

    console.log("Team ID", this.team);

  }
}
