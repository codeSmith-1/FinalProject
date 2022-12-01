import { Component, OnInit } from '@angular/core';
import { Food } from 'src/app/models/food';
import { FoodService } from 'src/app/services/food.service';

@Component({
  selector: 'app-food-crud',
  templateUrl: './food-crud.component.html',
  styleUrls: ['./food-crud.component.css']
})
export class FoodCrudComponent implements OnInit {
newFood: Food = new Food();
editFood: null | Food = null;
foods: Food[] = [];
filteredFoods: Food[] = [];
selected: null | Food = null;



  constructor(private foodService: FoodService) { }

  ngOnInit(): void {
  }

  reload(): void {
    this.foodService.index().subscribe({
      next: (foods) => {
        console.log(foods);
        this.foods = foods;
      },
      error: (fail) => {
        console.error('Food-crud error loading kid:');
        console.error(fail);
      },
    });
  }

  addFood(food: Food) {
    this.foodService.create(this.newFood).subscribe({
      next: (food) => {
        this.reload();
        this.newFood = new Food();
      },
      error: (fail) => {
        console.error('Food-crud.addFood(): error creating food record:');
        console.error(fail);
      },
    });
    this.reload();
  }

  deleteFood(id: number) {
    this.foodService.destroy(id).subscribe({
      next: (food) => {
        this.reload();
      },
      error: (fail) => {
        console.error('Food-crud.deleteKid(): error removing food record:');
        console.error(fail);
      },
    });
  }

  setEditFood(food: Food) {
    this.editFood = Object.assign({}, food);
  }

  getNumberOfFood() {
    return this.foods.length;
  }

  //   getKidsByRoom(roomName: string) {
  //     this.kids.forEach(kid => {
  //       if(kid.classroom.roomName === roomName)
  //       {
  //         return this.filteredKids;
  //       }
  //  //else?
  //     });
  //   }

  updateKid(id: number, food: Food) {
    this.foodService.update(id, food).subscribe({
      next: (food) => {
        this.reload();
        this.selected = food;
        this.editFood = null;
      },
      error: (fail) => {
        console.error('Food-crud updateFood(): error updating food record:');
        console.error(fail);
      },
    });
  }
}
