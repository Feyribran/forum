import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";
import {DataService} from "../data-service.service";
import {Topic} from "../model/topic";

@Component({
  selector: 'app-forum-view',
  templateUrl: './forum-view.component.html',
  styleUrls: ['./forum-view.component.css']
})

export class ForumViewComponent implements OnInit {

  users: User[];
  selectedUser: User;
  createdTopic: Topic = new Topic();

  constructor(public dataService: DataService) {

    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users : ', users))
  }

  details(user: User){
    this.selectedUser = user;
    this.createdTopic.admin = user;
    this.createdTopic.title = user.name+"'s topic";

    if(!this.selectedUser.topics){
      this.selectedUser.topics = [];
    }

    this.dataService
      .fetchUserWithTopics(user)
      .then(forumUser => this.selectedUser = forumUser)
      .then(console.log);

    console.log('you selected : ', user);
  }

  createTopic(){
    return this.dataService.createTopic(this.createdTopic)
      .then( () => this.selectedUser.topics.push(Object.assign({}, this.createdTopic)))
      .catch(e => alert(e.message));
  }

  ngOnInit() {
  }

}
