import { Component, OnInit } from '@angular/core';
import { windowWhen } from 'rxjs';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  loggedInUser: User | null = null;
  public isCollapsed: boolean = false;
  roleIsGuardian: boolean = false;

  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    if (this.auth.checkLogin()) {
      this.getLoggedInUser();
    }

    this.roleIsGuardian = this.isGuardian();

    window.addEventListener('load', (e) => {
      if(this.auth.checkLogin()){
      this.getLoggedInUser();
      }
    });
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
    console.log('in isGuardian');
    if (this.loggedInUser) {
      console.log('in first if statement');
      if (this.loggedInUser.role === 'user') {
        console.log('in2nd if statemen');
        return true;
      }
    }
    return false;
  }

  getLoggedInUser() {
    console.warn('In getLoggedInUser()');
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
        console.warn(this.loggedInUser);
      },
      error: (err) => {
        console.error('navbar-component.getLoggedInUser(): error logging in:');
        console.error(err);
        return false;
      },
    });
  }
}
