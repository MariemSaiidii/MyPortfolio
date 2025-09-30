import { Component, OnInit } from '@angular/core';
import { FormationService } from '../../services/formation.service';
import { Formation } from '../../models/formation.model';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html'
})
export class FormationComponent implements OnInit {
  formations: Formation[] = [];

  constructor(private formationService: FormationService) {}

  ngOnInit(): void {
    this.formationService.getAll().subscribe(data => this.formations = data);
  }
}
