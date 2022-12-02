import { Component, OnInit } from '@angular/core';
import { Adult } from 'src/app/models/adult';
import { Message } from 'src/app/models/message';
import { Staff } from 'src/app/models/staff';
import { AdultService } from 'src/app/services/adult.service';
import { MessageService } from 'src/app/services/message.service';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-message-crud',
  templateUrl: './message-crud.component.html',
  styleUrls: ['./message-crud.component.css']
})
export class MessageCrudComponent implements OnInit {

  constructor(private messageService: MessageService, private staffService: StaffService, private adultService: AdultService) {}
  newMessage: Message = new Message();
  messages: Message[] = [];
  selected: null | Message = null;
  allStaff: Staff[] | null = null;
  allAdults: Adult[] | null = null;

  ngOnInit(): void {
    this.reload();
    this.loadStaff();
    this.loadAdults();
  }

  reload():void {
    this.messageService.index().subscribe({
      next: (messages) => {
        console.log(messages);
        this.messages = messages;
      },
      error: (fail) => {
        console.error('Message-crud error laoding messages. ');
        console.error(fail);
      }
    })
  }

  addMessage(message:Message){
    this.messageService.create(this.newMessage).subscribe({
      next: (message) => {
        this.reload();
        this.newMessage = new Message();
      },
      error: (fail) => {
        console.error('Message-crud.addMessage(): error creating message');
        console.error(fail);
      },
    });
    this.reload();
  }

  deleteMessage(id:number) {
    this.messageService.destroy(id).subscribe({

      next:(message) => {
        this.reload();
      },
      error: (fail)=> {
        console.error('Message-crud.deleteMessage(): error removing message:');
        console.error(fail);
      }
    });
  }

  displayMessage(message: Message){
    this.selected = message;
  }

  loadStaff() {
    this.staffService.index().subscribe({
      next: (staff) => {
        this.allStaff = staff;
      },
      error: (error) => {
        console.error(
          'ShowNapByReport.view-daily-report component: error loading staff' +
            error
        );
      },
    });
  }

  loadAdults() {
    this.adultService.index().subscribe({
      next: (adult) => {
        this.allAdults = adult;
      },
      error: (fail) => {
        console.error('Message-crud.loadAdults(): error loading adults:');
        console.error(fail);
      },
    });
  }


}
