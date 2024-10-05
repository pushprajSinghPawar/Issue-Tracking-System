import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './home/header/header.component';
import { UserregisterComponent } from './userregister/userregister.component';
import { ExpertregisterComponent } from './expertregister/expertregister.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { ExpertdashboardComponent } from './expertdashboard/expertdashboard.component';
import { LogoutComponent } from './logout/logout.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { HeaderUserComponent } from './userdashboard/headeruser/headeruser.component';
import { AddissueComponent } from './addissue/addissue.component';
import { HeaderExpertComponent } from './expertdashboard/headerexpert/headerexpert.component';
import { UpdatefeedbackComponent } from './updatefeedback/updatefeedback.component';
import { AdminheaderComponent } from './admindashboard/adminheader/adminheader.component';
import { QuillModule } from 'ngx-quill';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserregisterComponent,
    ExpertregisterComponent,
    LoginComponent,
    UserdashboardComponent,
    ExpertdashboardComponent,
    LogoutComponent,
    HomeComponent,
    AdmindashboardComponent,
    HeaderUserComponent,
    AddissueComponent,
    HeaderExpertComponent,
    UpdatefeedbackComponent,
    AdminheaderComponent,
    PagenotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
