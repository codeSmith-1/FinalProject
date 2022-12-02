import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Bathroom } from 'src/app/models/bathroom';
import { BathroomType } from 'src/app/models/bathroom-type';
import { DailyReport } from 'src/app/models/daily-report';
import { Food } from 'src/app/models/food';
import { Mood } from 'src/app/models/mood';
import { MoodEntry } from 'src/app/models/mood-entry';
import { Nap } from 'src/app/models/nap';
import { ReportImage } from 'src/app/models/report-image';
import { Staff } from 'src/app/models/staff';
import { AuthService } from 'src/app/services/auth.service';
import { BathroomTypeService } from 'src/app/services/bathroom-type.service';
import { BathroomService } from 'src/app/services/bathroom.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { FoodService } from 'src/app/services/food.service';
import { MoodService } from 'src/app/services/mood.service';
import { NapService } from 'src/app/services/nap.service';
import { ReportImageService } from 'src/app/services/report-image.service';
import { StaffService } from 'src/app/services/staff.service';

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
  moodEntries: MoodEntry[] = [];
  nap: Nap = new Nap();
  bathrooms: Bathroom[] = [];
  closeResult: string = '';
  selected: DailyReport | null = null;
  newBathroom: Bathroom = new Bathroom();
  editBathroom: Bathroom | null = null;
  selectedBathroom: Bathroom | null = null;
  bathroomType: BathroomType[] | null = null;
  allStaff: Staff[] | null = null;
  newMood: MoodEntry = new MoodEntry();
  newImage: ReportImage = new ReportImage();
  newImageStaffId: number = 0;
  moodType: Mood = new Mood();
  newMoodMoodId: number = 0;
  moods: Mood[] = [];
  newFood: Food = new Food();

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
    private bathroomTypeService: BathroomTypeService,
    private staffService: StaffService,
    private reportImageService: ReportImageService,
    private moodService: MoodService
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
            this.loadBathroom(this.report);
            this.loadFood(this.report);
            this.loadImage(this.report);
            this.loadMoodEntries(this.report);
            this.loadNap(this.report);
            this.loadBathroomType();
            this.loadStaff();
            console.log(this.allStaff);
            this.loadMoods();
          },
          error: (fail) => {
            console.error('edit-report-component.ngOnInit: report not found' + fail);
            this.router.navigateByUrl('reportNotFound');
          },
        });
      }
    }
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

  loadImage(report: DailyReport) {
    this.imageService.showByReport(report.id).subscribe({
      next: (images) => {
        this.images = images;
      },
      error: (error) => {
        console.error(
          'ShowImagesByReport.view-daily-report component: error loading images' +
            error
        );
      },
    });
  }
  loadMoods() {
    this.moodService.show().subscribe({
      next: (moods) => {
        this.moods = moods;
      },
      error: (error) => {
        console.error(
          'ShowImagesByReport.view-daily-report component: error loading images' +
            error
        );
      },
    });
  }

  loadNap(report: DailyReport) {
    this.napService.showNapsByReport(report.id).subscribe({
      next: (nap) => {
        this.nap = nap;
        console.log(nap);
      },
      error: (error) => {
        console.error(
          'ShowNapByReport.view-daily-report component: error loading nap' +
            error
        );
      },
    });
  }

  loadBathroomType() {
    this.bathroomTypeService.showBathroomTypes().subscribe({
      next: (type) => {
        this.bathroomType = type;
        console.log(this.bathroomType);
      },
      error: (error) => {
        console.error(
          'ShowNapByReport.view-daily-report component: error loading nap' +
            error
        );
      },
    });
  }

  loadStaff() {
    this.staffService.index().subscribe({
      next: (staff) => {
        this.allStaff = staff;
      },
      error: (error) => {
        console.error(
          'ShowNapByReport.view-daily-report component: error loading staff' +
            error
        );
      },
    });
  }

  loadFood(report: DailyReport) {
    this.foodService.showByReport(report.id).subscribe({
      next: (food) => {
        this.food = food;
      },
      error: (error) => {
        console.error(
          'ShowFoodByReport.view-daily-report component: error loading food' +
            error
        );
      },
    });
  }

  loadBathroom(report: DailyReport) {
    this.bathroomService.showBathroomByReport(report.id).subscribe({
      next: (bathrooms) => {
        this.bathrooms = bathrooms;
      },
      error: (error) => {
        console.error(
          'loadBathroom.view-daily-report component: error loading bathroom' +
            error
        );
      },
    });
  }
  loadMoodEntries(report: DailyReport) {
    this.reportService.showMoodByReport(report.id).subscribe({
      next: (moodEntries) => {
        this.moodEntries = moodEntries;
      },
      error: (error) => {
        console.error(
          'ShowBathroomByReport.view-daily-report component: error loading bathroom' +
            error
        );
      },
    });
  }

  updateReport(report: DailyReport) {
    this.reportService.update(report).subscribe({
      next: (report) => {
        console.log('updateReport()' + report);
        // this.report = null;
      },
      error: (problem) => {
        console.error(
          'EditDailyReport-compontent.UpdateReport(): Error updating report.'
        );
      },
    });
  }

  addBathroom(bathroom: Bathroom) {
    bathroom.day = this.report;
    this.bathroomService.create(this.newBathroom).subscribe({
      next: (bathroom) => {
        this.loadBathroom(this.report);
        this.loadBathroomType();
        this.newBathroom = new Bathroom();
      },
      error: (fail) => {
        console.error(
          'Bathroom-crud.addBathroom(): error creating bathroom record:'
        );
        console.error(fail);
      },
    });
  }

  deleteBathroom(id: number) {
    this.bathroomService.destroy(id).subscribe({
      next: (bathroom) => {
        this.loadBathroom(this.report);
        this.loadBathroomType();
      },
      error: (fail) => {
        console.error(
          'Bathroom-crud.deleteBathroom(): error removing bathroom record:'
        );
        console.error(fail);
      },
    });
  }

  addReportImage(newImage: ReportImage, reportId: number, staffId: number) {
    this.reportImageService.create(newImage, reportId, staffId).subscribe({
      next: (image) => {
        this.loadImage(this.report);
        this.newImage = new ReportImage();
      },
      error: (fail) => {
        console.error(
          'editDailyReport.addReportImage(): error creating new image record:'
        );
        console.error(fail);
      },
    });
  }

  deleteReportImage(id: number) {
    this.reportImageService.destroy(id).subscribe({
      next: (image) => {
        this.loadImage(this.report);
      },
      error: (fail) => {
        console.error(
          'edit-dailyReport.deleteReportImage(): error removing report image record:'
        );
        console.error(fail);
      },
    });
  }

  addMood() {
    this.reportService
      .createMood(this.newMood, this.report.id, this.newMoodMoodId)
      .subscribe({
        next: (image) => {
          this.loadMoodEntries(this.report);
          this.newMood = new MoodEntry();
        },
        error: (fail) => {
          console.error(
            'editDailyReport.addMood()): error creating new Mood record:'
          );
          console.error(fail);
        },
      });
  }

  deleteMood(moodId: number) {
    this.reportService.destroyMood(moodId, this.report.id).subscribe({
      next: (mood) => {
        this.loadMoodEntries(this.report);
        this.loadMoods();
      },
      error: (fail) => {
        console.error(
          'edit-dailyReport.deleteMood(): error removing Mood record:'
        );
        console.error(fail);
      },
    });
  }

  // addFood() {
  //   if (this.newFood) {
  //     this.reportService.createFood(this.newFood, this.report.id).subscribe({
  //       next: (image) => {
  //         this.loadFood(this.report);
  //         this.newFood = new Food();
  //       },
  //       error: (fail) => {
  //         console.error(
  //           'editDailyReport.addMood()): error creating new Mood record:'
  //         );
  //         console.error(fail);
  //       },
  //     });
  //   }
  // }

  // deleteFood(foodId: number) {
  //   this.reportService.destroyFood(foodId).subscribe({
  //     next: (food) => {
  //       this.loadFood(this.report);
  //     },
  //     error: (fail) => {
  //       console.error(
  //         'edit-dailyReport.deleteMood(): error removing Mood record:'
  //       );
  //       console.error(fail);
  //     },
  //   });
  // }

  addNap(nap: Nap) {
      this.napService.create(nap, this.report.id).subscribe({
        next: (nap) => {
          console.log(nap)
          this.loadNap(this.report);
          this.nap = new Nap();
        },
        error: (fail) => {
          console.error(
            'editDailyReport.addNap()): error creating new Nap record:'
          );
          console.error(fail);
        },
      });
  }

  updateNap() {
      this.napService.update(this.nap).subscribe({
        next: (nap) => {
          this.loadNap(this.report);
          this.nap = new Nap();
        },
        error: (fail) => {
          console.error(
            'editDailyReport.updateNap()): error updating Nap record:'
          );
          console.error(fail);
        },
      });
  }

  deleteNap(napId: number) {
    this.napService.destroy(napId).subscribe({
      next: (nap) => {
        this.loadNap(this.report);
        this.nap = new Nap();
      },
      error: (fail) => {
        console.error(
          'edit-dailyReport.deleteNap(): error removing Nap record:'
        );
        console.error(fail);
      },
    });
  }

  addFood(food: Food) {
    this.foodService.create(food).subscribe({
      next: (food) => {
        this.loadFood(this.report);
        this.newFood = new Food();
      },
      error: (fail) => {
        console.error('Food-crud.addFood(): error creating food record:');
        console.error(fail);
      },
    });
    this.reload();
  }

  deleteFood(foodId: number) {
    this.foodService.destroy(foodId).subscribe({
      next: (food) => {
        this.loadFood(this.report);
      },
      error: (fail) => {
        console.error('Food-crud.deleteKid(): error removing food record:');
        console.error(fail);
      },
    });
  }
}
