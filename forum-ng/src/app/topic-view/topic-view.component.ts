import {Component, OnInit} from '@angular/core';
import {Topic} from "../model/topic";
import {DataService} from "../data-service.service";
import {User} from "../model/user";
import {Comment} from "../model/comment";

@Component({
  selector: 'app-topic-view',
  templateUrl: './topic-view.component.html',
  styleUrls: ['./topic-view.component.css']
})
export class TopicViewComponent implements OnInit {

  topics: Topic[];
  selectedTopic: Topic;
  selectedUser: User;
  createdTopic: Topic = new Topic();

  constructor(public dataService: DataService) {

    dataService.fetchTopics()
      .then(topics => this.topics = topics)
      .then(topics => console.log('Topics : ', topics))
  }

  ngOnInit() {
  }


  details(topic: Topic) {

    this.selectedTopic = topic;
    /*    this.createdTopic.admin = user;
     this.createdTopic.title = user.name + "'s topic";*/

    if (!this.selectedTopic.comments) {
      this.selectedTopic.comments = [];
    }

    this.dataService
      .fetchCommentsFromTopic(topic)
      .then(commentsTopic => topic.comments = commentsTopic)
      .then(console.log);

  }
}
