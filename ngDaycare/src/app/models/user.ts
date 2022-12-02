import { Adult } from "./adult";
import { Staff } from "./staff";

export class User {
  id: number;
  username: string;
  password: string;
  adult: Adult | undefined;
  staff: Staff | undefined;
  enabled: boolean | undefined;
  role: string | undefined;

  constructor(id: number = 0, username: string='', password: string='', enabled?: boolean, role?: string, adult?: Adult, staff?: Staff){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.adult = adult;
    this.staff = staff;
  }
}
