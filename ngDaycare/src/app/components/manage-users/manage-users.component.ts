import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Adult } from 'src/app/models/adult';
import { Staff } from 'src/app/models/staff';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { StaffService } from 'src/app/services/staff.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router, private staff: StaffService) { }

  ngOnInit(): void {
    this.loadAdults();
    this.reloadUser();
  }

  adults: Adult[] | null = null;
  inSessionStaff: Staff = new Staff();
  inSessionUser: User = new User();

  loadAdults() {
    this.staff.getAdults().subscribe({
      next: (adults) => {
        this.adults = adults;
      },
      error: (fail) => {
        console.error('ManageUsersComponent.loadAdults: error getting adults');
        console.error(fail);
      },
    });
  }

  reloadUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (loggedInUser) => {
        // route to success page
        this.inSessionUser = loggedInUser;
        console.log(this.inSessionUser);
      },
      error: (problem) => {
        console.error('ManageUsersComponent.register(): Error loading staff:');
      },
    });

    this.staff.show().subscribe({
      next: (loggedInStaff) => {
        this.inSessionStaff = loggedInStaff;
        console.log(this.inSessionStaff);
      },
      error: (problem) => {
        console.error('StaffHome error(): error showing staff');
        console.error(problem);
      },
    });
  }
}
