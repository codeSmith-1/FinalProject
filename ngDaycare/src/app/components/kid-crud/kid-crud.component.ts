import { Component, OnInit } from '@angular/core';
import { Classroom } from 'src/app/models/classroom';
import { Kid } from 'src/app/models/kid';
import { KidService } from 'src/app/services/kid.service';

@Component({
  selector: 'app-kid-crud',
  templateUrl: './kid-crud.component.html',
  styleUrls: ['./kid-crud.component.css'],
})
export class KidCRUDComponent implements OnInit {
  constructor(private kidService: KidService) {}
  newKid: Kid = new Kid();
  editKid: null | Kid = null;
  kids: Kid[] = [];
  filteredKids: Kid[] = [];
  selected: null | Kid = null;
  classrooms: Classroom[] = [];

  ngOnInit(): void {}

  reload(): void {
    this.kidService.index().subscribe({
      next: (kids) => {
        console.log(kids);
        this.kids = kids;
      },
      error: (fail) => {
        console.error('Kid-crud error loading kid:');
        console.error(fail);
      },
    });
  }

  addKid(kid: Kid) {
    this.kidService.create(this.newKid).subscribe({
      next: (kid) => {
        this.reload();
        this.newKid = new Kid();
      },
      error: (fail) => {
        console.error('Kid-crud.addKid(): error creating kid record:');
        console.error(fail);
      },
    });
    this.reload();
  }

  deleteKid(id: number) {
    this.kidService.destroy(id).subscribe({
      next: (kid) => {
        this.reload();
      },
      error: (fail) => {
        console.error('Kid-crud.deleteKid(): error removing kid record:');
        console.error(fail);
      },
    });
  }

  setEditKid(kid: Kid) {
    this.editKid = Object.assign({}, kid);
  }

  getNumberOfKids() {
    return this.kids.length;
  }

    getKidsByRoom(roomName: string) {
      for(let i = 0; i <= this.kids.length; i++) {
        if(this.kids[i].classroom.roomName === roomName)
        {
          this.filteredKids.push(this.kids[i]);
        }
      }
      return this.filteredKids;
    }

  updateKid(id: number, kid: Kid) {
    this.kidService.update(id, kid).subscribe({
      next: (kid) => {
        this.reload();
        this.selected = kid;
        this.editKid = null;
      },
      error: (fail) => {
        console.error('Kid-crud updateKid(): error updating kid record:');
        console.error(fail);
      },
    });
  }
}
