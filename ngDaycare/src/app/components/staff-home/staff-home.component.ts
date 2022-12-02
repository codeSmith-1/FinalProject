import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Adult } from 'src/app/models/adult';
import { DailyReport } from 'src/app/models/daily-report';
import { Kid } from 'src/app/models/kid';
import { Staff } from 'src/app/models/staff';
import { User } from 'src/app/models/user';
import { AdultService } from 'src/app/services/adult.service';
import { AuthService } from 'src/app/services/auth.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { KidService } from 'src/app/services/kid.service';
import { StaffService } from 'src/app/services/staff.service';
@Component({
  selector: 'app-staff-home',
  templateUrl: './staff-home.component.html',
  styleUrls: ['./staff-home.component.css'],
})
export class StaffHomeComponent implements OnInit {
  constructor(
    private auth: AuthService,
    private router: Router,
    private dailySvc: DailyReportService,
    private kidSvc: KidService,
    private adultSvc: AdultService,
    private staffSvc: StaffService,
    private modalService: NgbModal
  ) {}
  inSessionStaff: Staff = new Staff();
  inSessionUser: User = new User();
  kids: Kid[] = [];
  selected: null | Kid = null;
  emergencyContact: null | Adult = null;

  ngOnInit(): void {
    this.reloadUser();
    this.getKids();
  }

  // edit DR

  dailyReport(reportId: number) {
    console.log(reportId);
    this.router.navigateByUrl('editReport/' + reportId);
  }

  getEmergencyContact(kid: Kid){

  }

  getTodaysReportId(kid: Kid) {
    if (kid.dailyReport && kid.dailyReport.length > 0) {
      let report = kid.dailyReport.find((r) => {
        if (r && r.reportDate) {
          let today: Date = new Date(Date.now());
          let reportDate = r.reportDate.split('-');
          let arrDate = new Date(
            +reportDate[0],
            +reportDate[1]-1,
            +reportDate[2]
          );
          if (
            today.getFullYear() === arrDate.getFullYear() &&
            today.getMonth() === arrDate.getMonth() &&
            today.getDay() === arrDate.getDay()
          ) {
            return true;
          } else {
            return false;
          }
        } else {
          return false;
        }
      });
      if (report) {
        return report.id;
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }

  checkIn(kidId: number) {
    console.log(kidId);
    this.dailySvc.create(kidId).subscribe({
      next: (report) => {
        this.getKids();
        this.reloadUser();
        console.log(report);
      },
      error: (err) => {
        console.error('StaffHome.checkIn(): error creating report/checking in');
      },
    });
  }

  getKids() {
    this.kidSvc.index().subscribe({
      next: (kids) => {
        this.kids = kids;
        console.log(kids);
      },
      error: (problem) => {
        console.error('StaffHome.getKids(): Error loading kids:');
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
        console.error('StaffHomeComp.register(): Error loading staff:');
      },
    });

    this.staffSvc.show().subscribe({
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
