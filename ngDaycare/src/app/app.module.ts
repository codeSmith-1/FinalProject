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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
