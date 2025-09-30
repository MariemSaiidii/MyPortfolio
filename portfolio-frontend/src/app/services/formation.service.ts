import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Formation } from '../models/formation.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private apiUrl = 'http://localhost:8081/portfolio/formation';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Formation[]> {
    return this.http.get<Formation[]>(this.apiUrl);
  }

  add(f: Formation): Observable<Formation> {
    return this.http.post<Formation>(this.apiUrl, f);
  }

  update(f: Formation): Observable<Formation> {
    return this.http.put<Formation>(this.apiUrl, f);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
