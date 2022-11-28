export class BathroomType {
  id: number;
  status: string | undefined;

  constructor(id: number = 0, status?: string){
    this.id = id;
    this.status = status;
  }
}
