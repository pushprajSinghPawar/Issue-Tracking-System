import { Component } from '@angular/core';
import { User } from '../model/User';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userregister',
  templateUrl: './userregister.component.html',
  styleUrl: './userregister.component.scss'
})
export class UserregisterComponent {

  user: User = new User("", "", "", "");

  registrationForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    position: ['', Validators.required],
    companyName: ['', Validators.required],
    password: ['', Validators.required],
    confirmpassword: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private http:HttpClient, private router:Router) { }

  finalregister() {
    const formData = this.registrationForm.value;
    if (formData.name ==null || formData.name.length < 4 || formData.name.length > 10) {
      window.alert("Username must be between 4 and 10 characters");
      this.registrationForm.reset();
      return;
    }
    if (formData.password ==null || formData.password.length < 4 || formData.password.length > 10) {
      window.alert("Password must be between 4 and 10 characters");
      this.registrationForm.reset();
      return;
    }
    if (formData.position ==null || formData.position.length < 1 || formData.position.length > 50) {
      window.alert("Position must not be blank or more than 50 words");
      this.registrationForm.reset();
      return;
    }
    if (formData.companyName ==null || formData.companyName.length < 1 || formData.companyName.length > 50) {
      window.alert("Company must not be blank or more than 50 words");
      this.registrationForm.reset();
      return;
    }
    
    if(formData.confirmpassword==null  || formData.confirmpassword!== formData.password){
      window.alert("Passwords does'nt match");
      return;
    }

    this.user.name = formData.name;
    this.user.position = formData.position;
    this.user.companyName = formData.companyName;
    this.user.password = formData.password;

    this.http.post('http://localhost:9091/auth/addUser', this.user, { responseType: 'json' }).subscribe(
      (response) => {
        console.log('user added successfully:', response);
        window.alert('user added successfully');
        this.router.navigate(['/login']);
      },
      (error)=>{
        alert("cannot create User");
      }
    );


    console.log(this.user);
    this.registrationForm.reset();

  }


}
