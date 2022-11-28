import { Component, OnInit } from '@angular/core';
import { Adult } from 'src/app/models/adult';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit {

  constructor(private auth: AuthService, private adultSvc: AdultService) { }
  inSessionUser = new User();
  inSessionAdult = new Adult();
  ngOnInit(): void {
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
      },
      error: (problem) => {
        console.error(
          'UpdateAccountComponenet error():'
          );
          console.error(problem);
        },
      });
    }

}
