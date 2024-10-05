import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CredentialsAll } from '../model/CredentialAll';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { JwtService } from '../jwt-service.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  token="";
  loginForm = new FormGroup({
    username: new FormControl('', [
      Validators.required 
    ]),
    password: new FormControl('', [
      Validators.required
    ])
  });

  credential:CredentialsAll=new CredentialsAll("","");
  constructor(private router: Router, private http: HttpClient, private jwtService: JwtService) {}


  login()  {
    if (this.loginForm.invalid) {
      if(this.loginForm.value.username===null || this.loginForm.value.username===""){
        alert('Give username');
        return;
      }
      if(this.loginForm.value.password===null || this.loginForm.value.password===""){
        alert('Give password');
        return;
      }
    };
    if(this.loginForm.value.password!==null || this.loginForm.value.username!==null){
      var un = this.loginForm.value.username ?? "";
      var pw = this.loginForm.value.password ?? "";
      this.credential.username=un;
      this.credential.password=pw;
      console.log(this.credential);
      this.http.post('http://localhost:9091/auth/generateToken', {"username":this.credential.username,"password":this.credential.password}, { responseType: 'text' }).subscribe(
      (response) => {
        this.token=response;
        var mp = this.jwtService.getPayload(this.token);
        
        sessionStorage.setItem('token', this.token);
        sessionStorage.setItem('role', mp["ROLE"]);
        sessionStorage.setItem('credId', mp["credId"]);
        sessionStorage.setItem('username', mp["sub"]);

        if(sessionStorage.getItem("role")==="expert"){
          this.router.navigate(["/expertdashboard"]);
        }
        if(sessionStorage.getItem("role")==="admin"){
          this.router.navigate(["/admindashboard"]);
        }
        if(sessionStorage.getItem("role")==="user"){
          this.router.navigate(["/userdashboard"]);
        }
    
      },
      (error) => {
        window.alert('Bad Credentials');
        return ;
      }
    );

    
    }
    
   
    // this.loginForm.reset();
    // window.alert("user added sucessfully!!");

  }
  
}
