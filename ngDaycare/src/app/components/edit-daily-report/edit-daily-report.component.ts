import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Bathroom } from 'src/app/models/bathroom';
import { DailyReport } from 'src/app/models/daily-report';
import { Food } from 'src/app/models/food';
import { MoodEntry } from 'src/app/models/mood-entry';
import { Nap } from 'src/app/models/nap';
import { ReportImage } from 'src/app/models/report-image';
import { AuthService } from 'src/app/services/auth.service';
import { BathroomService } from 'src/app/services/bathroom.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { FoodService } from 'src/app/services/food.service';
import { NapService } from 'src/app/services/nap.service';
import { ReportImageService } from 'src/app/services/report-image.service';

@Component({
  selector: 'app-edit-daily-report',
  templateUrl: './edit-daily-report.component.html',
  styleUrls: ['./edit-daily-report.component.css'],
})
export class EditDailyReportComponent implements OnInit {
  report: DailyReport = new DailyReport();
  reports: DailyReport[] = [];
  images: ReportImage[] = [];
  food: Food[] = [];
  moods: MoodEntry[] = [];
  nap: Nap = new Nap();
  bathrooms: Bathroom[] = [];
  closeResult: string = "";

  constructor(
    private modalService: NgbModal,
    private route: ActivatedRoute,
    private router: Router,
    private foodService: FoodService,
    private bathroomService: BathroomService,
    private napService: NapService,
    private reportService: DailyReportService,
    private auth: AuthService,
    private imageService: ReportImageService,
  ) {}

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    console.log(id);
    if (id) {
      let reportId = Number.parseInt(id);
      if (isNaN(reportId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.reportService.show(reportId).subscribe({
          next: (report) => {
            this.report = report;
          },
          error: (fail) => {
            console.error('edit-report-component.ngOnInit: report not found');
            this.router.navigateByUrl('reportNotFound');
          },
        });
      }
    }
    this.LoadBathroom(this.report);
    this.LoadFood(this.report);
    this.LoadImage(this.report);
    this.LoadMood(this.report);
    this.LoadNap(this.report);
  }

  reload() {
    this.reportService.show(this.report.id).subscribe({
      next: (report) => {
        this.report = report;
      },
      error: (fail) => {
        console.error('ReportListComponent.reload: error getting report');
        console.error(fail);
      },
    });
  }

  LoadImage(selected: DailyReport){
    this.imageService.showByReport(selected.id).subscribe({
      next: (images) => {
        this.images = images;
      },
      error: (error) => {
        console.error('ShowImagesByReport.view-daily-report component: error loading images'+ error);
      },
    })
  }

  LoadNap(selected: DailyReport){
    this.napService.showNapsByReport(selected.id).subscribe({
      next: (nap) => {
        this.nap = nap;
      },
      error: (error) => {
        console.error('ShowNapByReport.view-daily-report component: error loading nap'+ error);
      },
    })
  }

  LoadFood(selected: DailyReport){
    this.foodService.showByReport(selected.id).subscribe({
      next: (food) => {
        this.food = food;
      },
      error: (error) => {
        console.error('ShowFoodByReport.view-daily-report component: error loading food'+ error);
      },
    })
  }

  LoadBathroom(selected: DailyReport){
    this.bathroomService.showBathroomByReport(selected.id).subscribe({
      next: (bathrooms) => {
        this.bathrooms = bathrooms;
      },
      error: (error) => {
        console.error('ShowMoodByReport.view-daily-report component: error loading mood'+ error);
      },
    })
  }
  LoadMood(selected: DailyReport){
    this.reportService.showMoodByReport(selected.id).subscribe({
      next: (moods) => {
        this.moods = moods;
      },
      error: (error) => {
        console.error('ShowBathroomByReport.view-daily-report component: error loading bathroom'+ error);
      },
    })
  }

  updateReport(report: DailyReport){
    this.reportService.update(report).subscribe({
      next: (report) => {
        console.log("updateReport()" + report)
        // this.report = null;
      },
      error: (problem) => {
        console.error(
          'EditDailyReport-compontent.UpdateReport(): Error updating report.'
        );
      },
    })
  }

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      },
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
