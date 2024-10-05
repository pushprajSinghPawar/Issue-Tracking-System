import { Component, enableProdMode } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Expert } from '../model/Expert';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-expertregister',
  templateUrl: './expertregister.component.html',
  styleUrls: ['./expertregister.component.scss']
})
export class ExpertregisterComponent {

  expert: Expert = new Expert("", "", []);

  registrationForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    password: ['', Validators.required],
    confirmpassword: ['', Validators.required],
    specialization: [''] // Assuming specialization is a string
  });

  specializationArray: string[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { }


  finalregister() {

    const formData = this.registrationForm.value;

    if (formData.name === null || formData.name.length < 4 || formData.name.length > 10) {
      window.alert("Username must be more than 3 and less than 10");
      return;
    }
    if (formData.password === null || formData.password.length < 4 || formData.password.length > 10) {
      window.alert("password must be more than 3 and less than 10");
      return;
    }
    if (formData.specialization === null || formData.specialization.length == 0) {
      console.log(formData.specialization);
      window.alert("Specialization must be given");
      return;
    }
    if(formData.confirmpassword==null  || formData.confirmpassword!== formData.password){
      window.alert("Passwords does'nt match");
      return;
    }
    this.expert.name = formData.name;
    this.expert.password = formData.password;
    this.specializationArray = formData.specialization.split(',').map((s: string) => s.trim());
    this.specializationArray = this.specializationArray.filter((str: string) => str !== '');
    this.expert.specialistIn = this.specializationArray;
    console.log(this.expert);
   
    this.http.post('http://localhost:9091/auth/addExpert', this.expert, { responseType: 'json' }).subscribe(
        (response) => {
          console.log('Expert added successfully:', response);
          window.alert('Expert added successfully');
          this.router.navigate(['/login']);
        },
        (error)=>{
          alert("Cannot crrreate Expert");
        }
      );
    


    this.specializationArray = [];
    this.registrationForm.reset();

  }

  updatespecs() {
    const formData = this.registrationForm.value;
    this.specializationArray = formData.specialization.split(',').map((s: string) => s.trim());
    this.specializationArray = this.specializationArray.filter((str: string) => str !== '');
  }

}
