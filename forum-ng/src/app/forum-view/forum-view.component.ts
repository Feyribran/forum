import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";
import {DataService} from "../data-service.service";

@Component({
  selector: 'app-forum-view',
  templateUrl: './forum-view.component.html',
  styleUrls: ['./forum-view.component.css']
})
export class ForumViewComponent implements OnInit {

  users: User[];
  selectedUser: User;

  constructor(public dataService: DataService) {

    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users : ', users))
  }

  details(user: User){
    this.selectedUser = user;
    console.log('you selected : ', user);
  }
  ngOnInit() {
  }

}
