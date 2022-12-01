import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { StaffHomeComponent } from './components/staff-home/staff-home.component';
import { GuardianHomeComponent } from './components/guardian-home/guardian-home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { RegisterStaffComponent } from './components/register-staff/register-staff.component';
import { RegisterAdultComponent } from './components/register-adult/register-adult.component';
import { SuccessComponent } from './components/success/success.component';
import { UpdateAccountComponent } from './components/update-account/update-account.component';
import { KidCRUDComponent } from './components/kid-crud/kid-crud.component';
import { ContactComponent } from './components/contact/contact.component';
import { ViewDailyReportComponent } from './components/view-daily-report/view-daily-report.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FoodCrudComponent } from './components/food-crud/food-crud.component';
import { BathroomCrudComponent } from './components/bathroom-crud/bathroom-crud.component';
import { EditDailyReportComponent } from './components/edit-daily-report/edit-daily-report.component';
import { MessageCrudComponent } from './components/message-crud/message-crud.component';
import { EmergencyContactPipe } from './pipes/emergency-contact.pipe';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    StaffHomeComponent,
    GuardianHomeComponent,
    NotFoundComponent,
    HomeComponent,
    NavBarComponent,
    RegisterStaffComponent,
    RegisterAdultComponent,
    SuccessComponent,
    UpdateAccountComponent,
    KidCRUDComponent,
    ContactComponent,
    ViewDailyReportComponent,
    UpdateAccountComponent,
    FoodCrudComponent,
    BathroomCrudComponent,
    EditDailyReportComponent,
    MessageCrudComponent,
    EmergencyContactPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
