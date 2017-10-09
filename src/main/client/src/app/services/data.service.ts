import { Injectable } from '@angular/core';
import { Http } from '@angular/http'
import 'rxjs/add/operator/toPromise';

import { Question } from '../entity/question'
import { Expert } from '../entity/expert'
import { ExpertCompetence } from '../entity/expert.competence'

@Injectable()
export class DataService {

  private questionsUrl = 'api/questions'

  constructor(private http: Http) { }

  public getQuestions(): Promise<Question[]> { 
    return this.http.get(this.questionsUrl)
    .toPromise()
    .then(response => response.json() as Question[])
    .catch(this.handleError);
  }

  public sendExpertsAnswers(experts: Expert[]): Promise<ExpertCompetence[]> {
    return this.http.post('api/experts/competence', experts)
    .toPromise()
    .then(response => response.json() as ExpertCompetence[])
    .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
