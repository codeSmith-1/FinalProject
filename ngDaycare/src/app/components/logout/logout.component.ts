import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  logoutUser: User = new User;

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  logout(logoutUser: User){
    console.log("Logged Out")
    this.auth.logout();
    this.router.navigateByUrl('/home');
  }
}
