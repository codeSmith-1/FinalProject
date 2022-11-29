import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Adult } from 'src/app/models/adult';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Staff } from 'src/app/models/staff';
import { StaffService } from 'src/app/services/staff.service';
@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit {
  @ViewChild("content",{static:true}) content:ElementRef | undefined;

  constructor(private auth: AuthService, private adultSvc: AdultService, private staffSvc: StaffService, private modalService: NgbModal) {}

  inSessionUser = new User();
  inSessionAdult = new Adult();
  closeResult: string = "";
  inSessionStaff = new Staff();

  ngOnInit(): void {
    this.reloadUser();

    }

    reloadUser(){
      this.auth.getLoggedInUser().subscribe({
        next: (loggedInUser) => {
          // route to success page
          this.inSessionUser = loggedInUser;
          console.log(this.inSessionUser)
        },
        error: (problem) => {
          console.error(
            'UpdateAccountnComponnet.register(): Error loading staff:'
          );
        },
      });

      this.adultSvc.show().subscribe({
        next: (loggedInAdult) => {
          this.inSessionAdult = loggedInAdult;
          console.log(this.inSessionAdult);
        },
        error: (problem) => {
          console.error(
            'UpdateAccountComponenet error(): showing adult'
            );
            console.error(problem);
          },
        });

        this.staffSvc.show().subscribe({
          next: (loggedInStaff) => {
            this.inSessionStaff = loggedInStaff;
            console.log(this.inSessionStaff);
          },
          error: (problem) => {
            console.error(
              'UpdateAccountComponenet error(): showing staff'
              );
              console.error(problem);
            },
          });
    }

    updateAdult(inSessionAdult: Adult){
      this.adultSvc.update(inSessionAdult).subscribe({
        next: (result) => {
          this.reloadUser();
        },
        error: (err) => {
          console.error('updateAccountComponent.updateAdult(): error');
          console.error(err);
        },
      });
    }

    updateStaff(inSessionStaff: Staff){
      this.staffSvc.update(inSessionStaff).subscribe({
        next: (result) => {
          this.reloadUser();
        },
        error: (err) => {
          console.error('updateAccountComponent.updateAdult(): error');
          console.error(err);
        },
      });
    }

    open(content: any) {
      this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        },
      );
    }


    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return `with: ${reason}`;
      }
    }
}
