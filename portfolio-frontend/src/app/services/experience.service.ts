import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Experience } from '../models/experience.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {
  private apiUrl = 'http://localhost:8081/portfolio/experience';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Experience[]> {
    return this.http.get<Experience[]>(this.apiUrl);
  }

  add(e: Experience): Observable<Experience> {
    return this.http.post<Experience>(this.apiUrl, e);
  }

  update(e: Experience): Observable<Experience> {
    return this.http.put<Experience>(this.apiUrl, e);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
