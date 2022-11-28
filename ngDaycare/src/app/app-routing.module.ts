import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './components/contact/contact.component';
import { GuardianHomeComponent } from './components/guardian-home/guardian-home.component';
import { HomeComponent } from './components/home/home.component';
import { KidCRUDComponent } from './components/kid-crud/kid-crud.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterAdultComponent } from './components/register-adult/register-adult.component';
import { RegisterStaffComponent } from './components/register-staff/register-staff.component';
import { StaffHomeComponent } from './components/staff-home/staff-home.component';
import { SuccessComponent } from './components/success/success.component';
import { UpdateAccountComponent } from './components/update-account/update-account.component';

const routes: Routes = [
 { path: '', pathMatch: 'full', redirectTo: 'home' },
 { path: 'home', component: HomeComponent },
 { path: 'contact', component: ContactComponent },
 { path: 'kidCrud', component: KidCRUDComponent },
 { path: 'success', component: SuccessComponent },
 { path: 'staffHome', component: StaffHomeComponent },
 { path: 'guardianHome', component: StaffHomeComponent },
 { path: 'registerAdult', component: RegisterAdultComponent },
 { path: 'registerStaff', component: RegisterStaffComponent },
 { path: 'success', component: SuccessComponent},
 { path: 'updateAccount', component: UpdateAccountComponent},
 { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
