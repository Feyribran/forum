import {User} from "./user";
import {Comment} from "./comment";

export class Topic{
  title: string;
  id: number;
  admin: User;
  comments: Comment[];

}
