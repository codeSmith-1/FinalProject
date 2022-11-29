import { Component, OnInit } from '@angular/core';
import { Bathroom } from 'src/app/models/bathroom';
import { BathroomService } from 'src/app/services/bathroom.service';

@Component({
  selector: 'app-bathroom-crud',
  templateUrl: './bathroom-crud.component.html',
  styleUrls: ['./bathroom-crud.component.css']
})
export class BathroomCrudComponent implements OnInit {

  constructor(private bathroomService: BathroomService) { }
  newBathroom: Bathroom = new Bathroom();
  editBathroom: null | Bathroom = null;
  bathrooms: Bathroom[] = [];
  selected: null | Bathroom = null;

  ngOnInit(): void {
  }

  reload(): void {
    this.bathroomService.index().subscribe({
      next: (bathrooms) => {
        console.log(bathrooms);
        this.bathrooms = bathrooms;
      },
      error: (fail) => {
        console.error('Bathroom-crud error loading bathroom:');
        console.error(fail);
      }
    })
  }

  addBathroom(bathroom: Bathroom) {
    this.bathroomService.create(this.newBathroom).subscribe({
      next: (bathroom) => {
        this.reload();
        this.newBathroom = new Bathroom();
      },
      error: (fail) => {
        console.error('Bathroom-crud.addBathroom(): error creating bathroom record:');
        console.error(fail);
      },
    });
    this.reload();
  }


  deleteBathroom(id: number) {
    this.bathroomService.destroy(id).subscribe({
      next: (bathroom) => {
        this.reload();
      },
      error: (fail) => {
        console.error('Bathroom-crud.deleteBathroom(): error removing bathroom record:');
        console.error(fail);
      },
    });
  }

  updateBathroom(id: number, bathroom: Bathroom) {
    this.bathroomService.update(id, bathroom).subscribe({
      next: (bathroom) => {
        this.reload();
        this.selected = bathroom;
        this.editBathroom = null;
      },
      error: (fail) => {
        console.error('Bathroom-crud updateBathroom(): error updating bathroom record:');
        console.error(fail);
      },
    });
  }

}
