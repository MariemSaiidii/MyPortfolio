import { Component, OnInit } from '@angular/core';
import { ExperienceService } from '../../services/experience.service';
import { Experience } from '../../models/experience.model';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html'
})
export class ExperienceComponent implements OnInit {
  experience: Experience[] = [];

  constructor(private experienceService: ExperienceService) {}

  ngOnInit(): void {
    this.experienceService.getAll().subscribe(data => this.experience = data);
  }
}
