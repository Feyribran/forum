import {User} from "./user";
/**
 * Created by AELION on 16/02/2018.
 */
export class Topic{
  title: string;
  id: number;
  admin: User;
  comments: Comment[];
}
