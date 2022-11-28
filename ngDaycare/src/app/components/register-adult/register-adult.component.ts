import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Adult } from 'src/app/models/adult';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register-adult',
  templateUrl: './register-adult.component.html',
  styleUrls: ['./register-adult.component.css']
})
export class RegisterAdultComponent implements OnInit {
  constructor(private auth: AuthService, private adultSvc: AdultService, private router: Router) {}

  newUser: User = new User();
  newAdult: Adult = new Adult();
  newAddress: Address = new Address();
  ngOnInit(): void {}

}
