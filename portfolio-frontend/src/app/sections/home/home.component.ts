import { Component, OnInit } from '@angular/core';
import { ContactService } from '../../services/contact.service';
import { ContactInfo } from '../../models/contact-info.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  contact: ContactInfo | null = null;

  constructor(private contactService: ContactService) {}

  ngOnInit(): void {
    this.contactService.getContact().subscribe(data => this.contact = data);
  }
}
