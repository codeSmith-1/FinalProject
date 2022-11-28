export class Classroom {
  id: number;
  roomName: string | undefined;

  constructor(id: number=0, roomName: string=""){
    this.id = id;
    this.roomName = roomName;

  }
}
