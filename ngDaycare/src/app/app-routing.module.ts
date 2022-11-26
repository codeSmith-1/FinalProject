import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuardianHomeComponent } from './components/guardian-home/guardian-home.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StaffHomeComponent } from './components/staff-home/staff-home.component';

const routes: Routes = [
 { path: '', pathMatch: 'full', redirectTo: 'home' },
 { path: 'home', component: HomeComponent },
 { path: 'staffHome', component: StaffHomeComponent },
 { path: 'guardianHome', component: StaffHomeComponent },
 { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
