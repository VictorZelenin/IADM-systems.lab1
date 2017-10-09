import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'init',
  templateUrl: './init.component.html',
  styleUrls: ['./init.component.css']
})
export class InitComponent implements OnInit {

  @Input() static quantityOfExperts:number;

  constructor() { }

  ngOnInit() {
  }
}
