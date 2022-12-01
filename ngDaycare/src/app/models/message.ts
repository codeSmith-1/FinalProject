import { User } from "./user";

export class Message {
  id: number;
  messageDate: string;
  content: string | undefined;
  recipient: User;
  sender: User;

  constructor(id: number = 0, messageDate: string = '', content: string = '', recipient: User= new User(), sender: User = new User()){
    this.id = id;
    this.messageDate = messageDate;
    this.content = content;
    this.recipient = recipient;
    this.sender = sender;
  }
}
