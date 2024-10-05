import { Component } from '@angular/core';
import { User } from '../../model/User';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-headerexpert',
  templateUrl: './headerexpert.component.html',
  styleUrl: './headerexpert.component.css'
})
export class HeaderExpertComponent {
feedbackshow() {

}
  currentUser:{
    "expertid": string,
    "name": string,
    "password": string,
    "specialistIn":  string [],
    "issuesids":  string [],
    "feedbackids": string [],
    "roles": string[]

  }={
    "expertid": "",
    "name": "",
    "password": "",
    "specialistIn": [],
    "issuesids": [],
    "feedbackids": [],
    "roles": []
}

constructor(private http: HttpClient) {

}
  
  ngOnInit() {
    
    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };

    this.http.get<{
      "expertid": string,
      "name": string,
      "password": string,
      "specialistIn":  string [],
      "issuesids":  string [],
      "feedbackids": string [],
      "roles": string[]      
    }>('http://localhost:9091/expert/showdetails', { headers , responseType:'json'}).subscribe(
      (response) => {
        this.currentUser.name = response["name"];
        this.currentUser.expertid = response["expertid"];
        this.currentUser.roles = response["roles"];
        this.currentUser.issuesids = response["issuesids"];
        this.currentUser.specialistIn = response["specialistIn"];
        console.log(this.currentUser);
      },
      (error) => {
        console.error('Error fetching issues', error);
      }
    );
  }

}
