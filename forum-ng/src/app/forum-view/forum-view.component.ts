import {Component, OnInit} from '@angular/core';
import {User} from "../model/user";
import {DataService} from "../data-service.service";
import {Topic} from "../model/topic";
import {Comment} from "../model/comment";

@Component({
  selector: 'app-forum-view',
  templateUrl: './forum-view.component.html',
  styleUrls: ['./forum-view.component.css']
})

export class ForumViewComponent implements OnInit {

  users: User[];
  selectedUser: User;
  createdTopic: Topic = new Topic();
  selectedTopic: Topic;
  createdComment: Comment = new Comment();


  constructor(public dataService: DataService) {

    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users : ', users))
  }

  details(user: User) {
    this.selectedUser = user;
    this.selectedTopic = null;
    this.createdTopic.admin = user;
    this.createdTopic.title = user.name + "'s topic";

    if (!this.selectedUser.topics) {
      this.selectedUser.topics = [];
    }

    if (!this.selectedUser.comments) {
      this.selectedUser.comments = [];
    }

    /*    // This one is for admin only
     this.dataService
     .fetchAdminWithTopics(user)
     .then(forumUser => this.selectedUser = forumUser)
     .then(console.log);*/

    // This one is for a user that commented topics
    this.dataService
      .fetchTopicsFromUser(user)
      .then(topics => this.selectedUser.topics = topics)
      .then(console.log);


  }

  getComments(topic: Topic) {
    this.selectedTopic = topic;
    this.createdComment.user = this.selectedUser;
    this.createdComment.topic = this.selectedTopic;

    if (!this.selectedTopic.comments) {
      this.selectedTopic.comments = [];
    }

    this.dataService
      .fetchCommentsFromTopic(topic)
      .then(commentsTopic => topic.comments = commentsTopic)
      .then(console.log);
    ;

  }

  createTopic() {
    return this.dataService.createTopic(this.createdTopic)
      .then(() => this.selectedUser.topics.push(Object.assign({}, this.createdTopic)))
      .catch(e => alert(e.message));
  }

  createComment() {
    return this.dataService.postComment(this.createdComment)
      .then(() => this.selectedTopic.comments.push(Object.assign({}, this.createdComment)))
      .catch(e => alert(e.message));
  }

  ngOnInit() {
  }

}
