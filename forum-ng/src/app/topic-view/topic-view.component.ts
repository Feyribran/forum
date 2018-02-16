import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {Topic} from "../model/topic";

@Component({
  selector: 'app-topic-view',
  templateUrl: './topic-view.component.html',
  styleUrls: ['./topic-view.component.css']
})
export class TopicViewComponent implements OnInit {

  topics: Topic[];

  constructor() { }

  ngOnInit() {
  }

}
