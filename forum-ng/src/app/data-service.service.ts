import {Injectable} from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Topic} from "./model/topic";
import  {Comment} from "./model/comment"

@Injectable()
export class DataService {

  constructor(public http: HttpClient) {
  }

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

  fetchTopics(): Promise<Topic[]> {
    return this.http
      .get('http://localhost:8080/forum/api/topics')
      .toPromise()
      .then(topics => {
        console.log(topics)
        return topics;
      })
      .then(data => data as Topic[])

  }

  fetchAdminWithTopics(user: User): Promise<User> {
    let url = 'http://localhost:8080/forum/api/users/' + user.id;
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
      .then(data => console.log('Success :) ', data))

  }

  fetchCommentsFromTopic(topic: Topic): Promise<Comment[]> {
    let url = 'http://localhost:8080/forum/api/comments/' + topic.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('topic with comment :', data);
        return data as Comment[];
      })
  }

  fetchTopicsFromUser(user: User): Promise<Topic[]> {
    let url = 'http://localhost:8080/forum/api/topics/' + user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('topics containing user:', data);
        return data as Topic[];
      })
  }

  postComment(comment: Comment) {
    let url = 'http://localhost:8080/forum/api/comments';
    let dto = { // Data Transfer Object pour Jax-B
      content: comment.content,
      user: {
        id : comment.user.id,
      },
      topic : {
        id : comment.topic.id
      }
    }

    console.log(dto);

    console.log('Sending commentDTO' + dto);

    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => console.log('Success :) ', data))

  }


  /*  fetchCommentsFromUser(user: User): Promise<Comment[]>{
   let url = 'http://localhost:8080/forum/api/comments/'+user.id;
   return this.http
   .get(url)
   .toPromise()
   .then(data => {
   console.log('user with comment :',  data);
   return data as Comment[];
   })
   }*/
}
