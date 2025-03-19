import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NbaService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getTodayGames(): Observable<any> {
    return this.http.get(`${this.baseUrl}/games/today`);
  }

  getTeams(): Observable<any> {
    return this.http.get(`${this.baseUrl}/teams`);
  }

  getTeamById(id: string | null): Observable<any> {
    console.log("ID:", id);
    console.log("Route "+this.baseUrl+"/teams/"+id);
    return this.http.get<any>(`${this.baseUrl}/teams/${id}`);
  }
}
