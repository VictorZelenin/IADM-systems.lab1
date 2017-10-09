import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { InitComponent } from './components/init/init.component';
import { ExpertComponent } from './components/expert/expert.component';
import { QuestionComponent } from './components/question/question.component'

import { DataService } from './services/data.service';
import { ResultComponent } from './components/result/result.component'

@NgModule({
  declarations: [
    AppComponent,
    InitComponent,
    ExpertComponent,
    QuestionComponent,
    ResultComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: '',
        component: InitComponent
      },
      {
        path: 'expert',
        component: ExpertComponent
      },
     {
       path: 'questions',
       component: QuestionComponent
     }, 
     {
       path: 'results',
       component: ResultComponent
     }
    ])
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
