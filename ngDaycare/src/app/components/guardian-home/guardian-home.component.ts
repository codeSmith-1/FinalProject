import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Kid } from 'src/app/models/kid';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-guardian-home',
  templateUrl: './guardian-home.component.html',
  styleUrls: ['./guardian-home.component.css']
})
export class GuardianHomeComponent implements OnInit {

  constructor(private auth: AuthService, private adultSvc: AdultService, private staffSvc: StaffService, private modalService: NgbModal) { }
  closeResult: string = "";
  newKid: Kid = new Kid();

  ngOnInit(): void {
    // load children of adult
  }






addKid(newKid: Kid){

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
