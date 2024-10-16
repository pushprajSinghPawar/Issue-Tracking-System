import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {

  constructor(private router:Router){

  }
  ngOnInit() {
    sessionStorage.clear();
    this.router.navigate(["/"]);
    window.alert("User signed Off");
  }
}
