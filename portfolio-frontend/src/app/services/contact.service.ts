import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ContactInfo } from '../models/contact-info.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private apiUrl = 'http://localhost:8081/portfolio/contact';

  constructor(private http: HttpClient) {}

  getContact(): Observable<ContactInfo> {
    return this.http.get<ContactInfo>(this.apiUrl);
  }

  updateContact(contact: ContactInfo): Observable<ContactInfo> {
    return this.http.put<ContactInfo>(this.apiUrl, contact);
  }
}
