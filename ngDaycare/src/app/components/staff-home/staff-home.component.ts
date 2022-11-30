import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';
import { KidService } from 'src/app/services/kid.service';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-staff-home',
  templateUrl: './staff-home.component.html',
  styleUrls: ['./staff-home.component.css']
})
export class StaffHomeComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router, private kidSvc: KidService, private adultSvc: AdultService, private staffSvc: StaffService, private modalService: NgbModal) { }

  inSessionUser: User = new User();
  ngOnInit(): void {
  }

}
