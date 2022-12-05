import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { NavBarComponent } from '../nav-bar/nav-bar.component';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  logoutUser: User = new User;

  constructor(private auth: AuthService, private router: Router, private nav: NavBarComponent) { }

  ngOnInit(): void {
  }

  logout(logoutUser: User){
    console.log("Logged Out")
    this.auth.logout();
    // window.location.reload();
    this.nav.loggedInUser = null;
    // this.nav.ngOnInit();
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigateByUrl('/home');
  }
}
