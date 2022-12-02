import { DailyReport } from "./daily-report";
import { FoodType } from "./food-type";

export class Food {
  id: number;
  description: string | undefined;
  day: DailyReport;
  foodType: FoodType;

  constructor(id: number = 0, day: DailyReport = new DailyReport(), description: string = '', foodType: FoodType = new FoodType()){
    this.id = id;
    this.description = description;
    this.day = day;
    this.foodType = foodType;
  }
}
