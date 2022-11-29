import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Adult } from 'src/app/models/adult';
import { Kid } from 'src/app/models/kid';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';
import { KidService } from 'src/app/services/kid.service';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-guardian-home',
  templateUrl: './guardian-home.component.html',
  styleUrls: ['./guardian-home.component.css']
})
export class GuardianHomeComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router, private kidSvc: KidService, private adultSvc: AdultService, private staffSvc: StaffService, private modalService: NgbModal) { }
  closeResult: string = "";
  newKid: Kid = new Kid();
  inSessionUser: User = new User();
  inSessionAdult: Adult = new Adult();
  kids: Kid[] = [];
  selected: null | Kid = null;


  ngOnInit(): void {
    this.reloadUser();
    this.getKids();
  }

addKid(newKid: Kid){
  this.kidSvc.create(newKid).subscribe({
    next: (result) => {
      this.reloadUser();
      this.router.navigateByUrl('/guardianHome');

    },
    error: (err) => {
      console.error('updateAccountComponent.addKid(): error');
      console.error(err);
    },
  });
}

updateKid(selected: Kid){
  this.kidSvc.update(selected).subscribe({
    next: (kid) => {
      console.log("updateKid():" + kid)
      this.selected = null;
      this.router.navigateByUrl('/guardianHome');
    },
    error: (problem) => {
      console.error(
        'GuardianHomeComponent.updateKid(): Error updating kid:'
      );
    },
  })
}

getKids(){
  this.adultSvc.getKids().subscribe({
    next: (kids) => {
      // route to success page
      this.kids = kids;
      console.log(this.kids)
    },
    error: (problem) => {
      console.error(
        'GuardianHomeComponent.getKids(): Error loading kids:'
      );
    },
  });
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

    // this.staffSvc.show().subscribe({
    //   next: (loggedInStaff) => {
    //     this.inSessionStaff = loggedInStaff;
    //     console.log(this.inSessionStaff);
    //   },
    //   error: (problem) => {
    //     console.error(
    //       'UpdateAccountComponenet error(): showing staff'
    //       );
    //       console.error(problem);
    //     },
    //   });
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

  updateKidModal(updateContent: any) {
    this.modalService.open(updateContent, { ariaLabelledBy: 'modal-basic-title' }).result.then(
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
