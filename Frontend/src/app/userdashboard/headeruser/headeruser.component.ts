import { Component } from '@angular/core';
import { User } from '../../model/User';
import { HttpClient } from '@angular/common/http';
import { JwtService } from '../../jwt-service.service';

@Component({
  selector: 'app-headeruser',
  templateUrl: './headeruser.component.html',
  styleUrl: './headeruser.component.css'
})
export class HeaderUserComponent {
  currentUser: {
    "username": string,
    "role": string,
    "userid": string,
    "companyName": string,
    "position": string
  }={
    "username": "",
    "role": "",
    "userid": "",
    "companyName": "",
    "position": ""
  };
  clock: number = 0;
  constructor(private http: HttpClient, private jwtService: JwtService) {

  }
  extend(){
    const new_token  = this.jwtService.getPayload(sessionStorage.getItem('token')!);
    console.log(new_token);
  }
  ngOnInit() {
    
    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };

    this.http.get<{
      "name": string,
      "roles": string[],
      "userid": string,
      "companyName": string,
      "position": string
    }>('http://localhost:9091/user/showdetails', { headers , responseType:'json'}).subscribe(
      (response) => {
        this.currentUser.username = response["name"];
        this.currentUser.companyName = response["companyName"];
        this.currentUser.position = response["position"];
        this.currentUser.userid = response["userid"];
        this.currentUser.role = response["roles"][0];
      },
      (error) => {
        console.error('Error fetching issues', error);
      }
    );
  }


}
