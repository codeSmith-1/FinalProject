import { Component, OnInit } from '@angular/core';
import { Adult } from 'src/app/models/adult';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Address } from 'src/app/models/address';
@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit {

  constructor(private auth: AuthService, private adultSvc: AdultService, private modalService: NgbModal) {}
  inSessionUser = new User();
  inSessionAdult = new Adult();
  closeResult: string = "";

  ngOnInit(): void {
    this.reloadUser();
    }

    reloadUser(){
      this.auth.getLoggedInUser().subscribe({
        next: (loggedInUser) => {
          // route to success page
          this.inSessionUser = loggedInUser;
        },
        error: (problem) => {
          console.error(
            'UpdateAccountnComponnet.register(): Error loading staff:'
          );
          console.error(problem);
        },
      });

      this.adultSvc.show().subscribe({
        next: (loggedInAdult) => {
          this.inSessionAdult = loggedInAdult;
          console.log(this.inSessionAdult);
        },
        error: (problem) => {
          console.error(
            'UpdateAccountComponenet error():'
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
