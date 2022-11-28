export class Message {
  id: number;
  messageDate: string;
  content: string | undefined;

  constructor(id: number = 0, messageDate: string = '', content?: string){
    this.id = id;
    this.messageDate = messageDate;
    this.content = content;
  }
}
