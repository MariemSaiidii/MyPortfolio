import { Component, OnInit } from '@angular/core';
import { SkillService } from '../../services/skill.service';
import { Skill } from '../../models/skill.model';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html'
})
export class SkillsComponent implements OnInit {
  skills: Skill[] = [];

  // Pagination properties
  skillsPerPage = 5;
  currentPage = 1;

  constructor(private skillService: SkillService) {}

  ngOnInit(): void {
    this.skillService.getAll().subscribe(data => this.skills = data);
  }

  // Compute skills to display on current page
  get paginatedSkills(): Skill[] {
    const startIndex = (this.currentPage - 1) * this.skillsPerPage;
    return this.skills.slice(startIndex, startIndex + this.skillsPerPage);
  }

  get totalPages(): number {
    return Math.ceil(this.skills.length / this.skillsPerPage);
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }
}
