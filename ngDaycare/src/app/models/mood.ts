import { MoodEntry } from "./mood-entry";

export class Mood {
  id: number;
  description: string | undefined;
  moodEntry: MoodEntry | null;

  constructor(id: number = 0, description: string = "", moodEntry: MoodEntry | null = null){
    this.id = id;
    this.description = description;
    this.moodEntry = moodEntry;
  }
}
