import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { FormationComponent } from './sections/formation/formation.component';
import { ExperienceComponent } from './sections/experience/experience.component';
import { ProjectsComponent } from './sections/projects/projects.component';
import { CoursesComponent } from './sections/courses/courses.component';
import { SkillsComponent } from './sections/skills/skills.component';
import { FooterComponent } from './sections/footer/footer.component';
import { SidebarComponent } from './sections/sidebar/sidebar.component';
import { HomeComponent } from './sections/home/home.component';
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'experience', component: ExperienceComponent },
  { path: 'formation', component: FormationComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'skills', component: SkillsComponent }
];
@NgModule({
  declarations: [
    AppComponent,
    
    FormationComponent,
    ExperienceComponent,
    ProjectsComponent,
    CoursesComponent,
    SkillsComponent,
    FooterComponent,
    SidebarComponent,
    HomeComponent
  ],
   imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
