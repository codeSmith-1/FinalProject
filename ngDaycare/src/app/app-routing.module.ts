import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BathroomCrudComponent } from './components/bathroom-crud/bathroom-crud.component';
import { ContactComponent } from './components/contact/contact.component';
import { EditDailyReportComponent } from './components/edit-daily-report/edit-daily-report.component';
import { FoodCrudComponent } from './components/food-crud/food-crud.component';
import { GuardianHomeComponent } from './components/guardian-home/guardian-home.component';
import { HomeComponent } from './components/home/home.component';
import { KidCRUDComponent } from './components/kid-crud/kid-crud.component';
import { LoginComponent } from './components/login/login.component';
import { ManageUsersComponent } from './components/manage-users/manage-users.component';
import { MessageCrudComponent } from './components/message-crud/message-crud.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterAdultComponent } from './components/register-adult/register-adult.component';
import { RegisterStaffComponent } from './components/register-staff/register-staff.component';
import { StaffHomeComponent } from './components/staff-home/staff-home.component';
import { SuccessComponent } from './components/success/success.component';
import { UpdateAccountComponent } from './components/update-account/update-account.component';
import { ViewDailyReportComponent } from './components/view-daily-report/view-daily-report.component';
import { Message } from './models/message';

const routes: Routes = [
 { path: '', pathMatch: 'full', redirectTo: 'home' },
 { path: 'home', component: HomeComponent },
 { path: 'contact', component: ContactComponent },
 { path: 'kidCrud', component: KidCRUDComponent },
 { path: 'success', component: SuccessComponent },
 { path: 'staffHome', component: StaffHomeComponent },
 { path: 'guardianHome', component: GuardianHomeComponent },
 { path: 'register', component: RegisterAdultComponent },
 { path: 'registerStaff', component: RegisterStaffComponent },
 { path: 'success', component: SuccessComponent},
 { path: 'updateAccount', component: UpdateAccountComponent},
 { path: 'viewReports', component: ViewDailyReportComponent},
 { path: 'bathroomCrud', component: BathroomCrudComponent},
 { path: 'foodCrud', component: FoodCrudComponent},
 { path: 'editReport/:id', component: EditDailyReportComponent},
 { path: 'messages', component: MessageCrudComponent},
 { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
