import {Topic} from "./topic";
import {Comment} from "./comment";

/**
 * Created by AELION on 16/02/2018.
 */

export class User{
  name: string;
  id: number;
  topics: Topic[] = [];
  comments: Comment[] = [];

}
