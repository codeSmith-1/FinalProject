import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Classroom } from 'src/app/models/classroom';
import { Staff } from 'src/app/models/staff';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { StaffService } from 'src/app/services/staff.service';
@Component({
  selector: 'app-register-staff',
  templateUrl: './register-staff.component.html',
  styleUrls: ['./register-staff.component.css'],
})
export class RegisterStaffComponent implements OnInit {

  constructor(private auth: AuthService, private staff: StaffService, private router: Router) {}

  newUser: User = new User();
  newStaff: Staff = new Staff();
  classroom: Classroom = new Classroom();
  classrooms: Classroom[] = [];

  ngOnInit(): void {
  }

  // get list of classrooms
  reload() {
    this.staff.indexClassroom().subscribe({
      next: (data) => {
        this.classrooms = data;
      },
      error: (fail) => {
        console.error('RegisterStaffComponent.reload: error getting classrooms');
        console.error(fail);
      },
    });
  }

  // register(newUser: User): void {
  //   console.log('Registering user:');
  //   console.log(newUser);

  //   this.auth.register(newUser).subscribe({
  //     next: (registeredUser) => {
  //       this.auth.login(newUser.username, newUser.password).subscribe({
  //         next: (loggedInUser) => {
  //           this.router.navigateByUrl('/todo');
  //         },
  //         error: (problem) => {
  //           console.error(
  //             'RegisterComponent.register(): Error logging in user:'
  //           );
  //           console.error(problem);
  //         },
  //       });
  //     },
  //     error: (fail) => {
  //       console.error(
  //         'RegisterComponent.register(): Error registering account'
  //       );
  //       console.error(fail);
  //     },
  //   });
  // }

  registerStaff(newStaff: Staff, newUser: User) {
    this.auth.register(newUser).subscribe({
      next: (registeredUser) => {
        newStaff.user = registeredUser;
        // newStaff.classroom = this.classroom;
        // staff svc create
        this.staff.create(newStaff, newUser).subscribe({
          next: (createdStaff) => {
            // route to success page
            this.router.navigateByUrl('/success');
          },
          error: (problem) => {
            console.error(
              'RegisterStaff.register(): Error creating staff:'
            );
            console.error(problem);
          },
        });
      },
      error: (fail) => {
        console.error(
          'RegisterStaff.register(): Error registering user'
        );
        console.error(fail);
      },
    });
  }
}
