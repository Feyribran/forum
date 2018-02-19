import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Topic} from "./model/topic";


@Injectable()
export class DataService {

  constructor(public http: HttpClient) { }

  fetchUsers(): Promise<User[]> {
    return this.http
      .get('http://localhost:8080/forum/api/users')
      .toPromise()
      .then(users => {
        console.log(users)
        return users;
      })
      .then(data => data as User[])

  }

  fetchUserWithTopics(user: User):Promise<User>{
    let url = 'http://localhost:8080/forum/api/users/'+user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with topic: ', data);
        return data as User;
      })
  }

  createTopic(topic: Topic) {
    let url = 'http://localhost:8080/forum/api/topics';
    let dto = { // Data Transfer Object pour Jax-B
      title: topic.title,
      admin: topic.admin
    }

    console.log('Sending topicDTO' + dto);

    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success :) ',data))

  }

}
