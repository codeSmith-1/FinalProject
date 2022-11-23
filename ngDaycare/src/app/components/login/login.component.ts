import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User = new User();

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
  }

  login(loginUser: User){
    console.log('user logged in');
    this.auth.login(loginUser.username, loginUser.password).subscribe({
      next:(loggedInUser)=> {
        // add logic for user type
        if (loggedInUser.role == ("staff") || loggedInUser.role == ("admin")){
        this.router.navigateByUrl('/staffHome');
        } else if (loggedInUser.role == ("user")){
          this.router.navigateByUrl('/guardianHome');
        }
      },
      error: (err)=>{
        console.error('LoginComponent.login(): error logging in:');
        console.error(err);
      }
    });
  }
}
