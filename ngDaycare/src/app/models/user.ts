export class User {
  id: number;
  username: string | undefined;
  password: string | undefined;
  enabled: boolean | undefined;
  role: string | undefined;

  constructor(id: number = 0, username?: string, password?: string, enabled?: boolean, role?: string){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
  }
}
