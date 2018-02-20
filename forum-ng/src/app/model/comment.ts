import {User} from "./user";
import {Topic} from "./topic";
/**
 * Created by AELION on 19/02/2018.
 */
export class Comment{

  id: number;
  content: string;
  user: User;
  topic: Topic;
}
