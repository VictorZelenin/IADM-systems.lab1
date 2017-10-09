import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

import { Question } from '../../entity/question'
import { Expert } from '../../entity/expert'
import { ExpertCompetence } from '../../entity/expert.competence'
import { ExpertAnswer } from '../../entity/expert.answer'

import { DataService } from '../../services/data.service'

import { InitComponent } from '../init/init.component'
import { ExpertComponent } from '../expert/expert.component'

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
  providers: [DataService]
})
export class QuestionComponent implements OnInit {
  counter = 0;
  questions: Question[];
  experts: Expert[];
  currentExpert: Expert;
  expertAnswers: ExpertAnswer[];

  static expertCompetences: ExpertCompetence[];

  constructor(private dataService: DataService, private router: Router) { }

  ngOnInit() {
    this.dataService.getQuestions().then(questions => this.questions = questions);
    this.currentExpert.name = ExpertComponent.name;
  }

  saveExpertAnswers(expert:Expert): void {
    if (this.counter < InitComponent.quantityOfExperts) {
      this.experts.push(expert);
      this.router.navigateByUrl("expert")
    } else {
      this.counter = 0;
      this.experts = []; 
      var competences = this.dataService.sendExpertsAnswers(this.experts);
      console.log(competences);
      this.router.navigateByUrl("results");
    }
    this.counter++;    
  }
}