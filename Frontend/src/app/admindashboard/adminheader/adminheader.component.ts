import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-adminheader',
  templateUrl: './adminheader.component.html',
  styleUrl: './adminheader.component.css'
})
export class AdminheaderComponent {
  currentUser={
    "username":"",
    "role":"",
    "userid":""
  };
  ngOnInit(){
    this.currentUser={
      "username":sessionStorage.getItem("username")!,
      "role":sessionStorage.getItem("role")!,
      "userid":sessionStorage.getItem("credId")!
    };
  }
  @Output() notifyParent1: EventEmitter<any> = new EventEmitter<any>();
  @Output() notifyParent2: EventEmitter<any> = new EventEmitter<any>();
  @Output() notifyParent3: EventEmitter<any> = new EventEmitter<any>();
  @Output() notifyParent4: EventEmitter<any> = new EventEmitter<any>();

  generateReport() {
    this.notifyParent1.emit();
  }
  generateReportExpert(){
    this.notifyParent2.emit();

  }
  generateReportUsers(){
    this.notifyParent3.emit();
  }
  showWallet(){
    this.notifyParent4.emit();
  }
}

