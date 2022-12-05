import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { NavBarComponent } from '../nav-bar/nav-bar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User = new User();

  constructor(private auth: AuthService, private router: Router, private route: ActivatedRoute, private nav: NavBarComponent) { }
  invalidLogin = false;
  ngOnInit(): void {
  }

  // reload() {
  //   this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  //   this.router.onSameUrlNavigation = 'reload';
  //   this.router.navigate(['./'], { relativeTo: this.route });
  // }

  login(loginUser: User){
    this.auth.login(loginUser.username, loginUser.password).subscribe({
      next:(loggedInUser)=> {
        this.nav.ngOnInit();
        // add logic for user type
        this.invalidLogin = false;
        // window.location.reload();
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        if (loggedInUser.role == ("staff") || loggedInUser.role == ("admin")){
        this.router.navigateByUrl('/staffHome');
        } else if (loggedInUser.role == ("user")){
          this.router.navigateByUrl('/guardianHome');
        }
      },

      error: (err)=>{
        this.invalidLogin = true;
        console.error('LoginComponent.login(): error logging in:');
        console.error(err);
        return false;
      }
    });
  }
}
