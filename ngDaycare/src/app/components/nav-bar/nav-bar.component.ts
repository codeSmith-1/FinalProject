import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  loggedInUser: User | null = null;

  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.getLoggedInUser();
  }

  loggedIn() {
    return this.auth.checkLogin();
  }
  isStaff() {
    if (this.loggedInUser) {
      if (this.loggedInUser.role === 'staff') {
        return true;
      }
    }
    return false;
  }
  isGuardian() {
    if (this.loggedInUser) {
      if (this.loggedInUser.role === 'user') {
        return true;
      }
    }
    return false;
  }

  getLoggedInUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (err) => {
        console.error('LoginComponent.login(): error logging in:');
        console.error(err);
        return false;
      },
    });
  }
}
