import { Adult } from "./adult";
import { Kid } from "./kid";

export class Guardian {
  adult: Adult | null = null;
  kid: Kid | null = null;

  constructor(adult: Adult , kid: Kid){
    this.adult = adult;
    this.kid = kid;
  }
}
