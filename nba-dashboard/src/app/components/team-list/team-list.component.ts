import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbaService } from '../../services/nba.service';
//import { HttpClientModule } from '@angular/common/http';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-team-list',
  standalone: true,
  imports: [CommonModule, MatListModule, RouterModule],
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.scss']
})
export class TeamListComponent implements OnInit {
  teams: any[] = [];

  constructor(private nbaService: NbaService) {}

  ngOnInit(): void {
    this.nbaService.getTeams().subscribe(data => {
      this.teams = data;
      //console.log("This the data", this.teams);
    });
  }
}
