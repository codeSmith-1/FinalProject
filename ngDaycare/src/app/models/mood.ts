import { MoodEntry } from "./mood-entry";

export class Mood {
  id: number;
  description: string | undefined;
  moodEntry: MoodEntry;

  constructor(id: number = 0, description: string, moodEntry: MoodEntry){
    this.id = id;
    this.description = description;
    this.moodEntry = moodEntry;
  }
}
