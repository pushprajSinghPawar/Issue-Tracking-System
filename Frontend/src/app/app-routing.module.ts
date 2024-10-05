import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExpertregisterComponent } from './expertregister/expertregister.component';
import { UserregisterComponent } from './userregister/userregister.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { HomeComponent } from './home/home.component';
import { ExpertdashboardComponent } from './expertdashboard/expertdashboard.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { AddissueComponent } from './addissue/addissue.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';

const routes: Routes = [
  { path:'', component:HomeComponent},
  { path: 'expertregister', component: ExpertregisterComponent },
  { path: 'userregister', component: UserregisterComponent },
  { path: 'login', component:LoginComponent},
  { path: 'logout', component:LogoutComponent},
  { path:'userdashboard', component:UserdashboardComponent},
  { path:'expertdashboard', component:ExpertdashboardComponent},
  { path:'admindashboard', component:AdmindashboardComponent},
  { path:'addissue', component:AddissueComponent},
  { path: '**', pathMatch: 'full', component: PagenotfoundComponent }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
