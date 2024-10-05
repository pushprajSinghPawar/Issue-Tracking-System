import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { CredentialsAll } from '../model/CredentialAll';
import { Router } from '@angular/router';
import { JwtService } from '../jwt-service.service';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrl: './admindashboard.component.css'
})
export class AdmindashboardComponent {
  creds: any[] = [];
  analyticsData: any = {
    "RESOLVED": "NA",
    "UNRESOLVED": "NA"
  };
  analyticsData2: { [key: string]: { total: number; resolved: number } } = {};
  analyticsData3: { [key: string]: { unresolved: number; resolved: number } } = {};
  displayalluser = false;
  displayissueanalytics = false;
  disdisplayissueanalyticsbyuser = false;
  displayissueanalyticsbyexpert = true;
  expired: Date = new Date();
  amountWallet:{"userid":string,"transactionId":string,"date":Date}[]=[];

  constructor(private http: HttpClient, private router: Router, private jwtservice: JwtService) { }

  ngOnInit() {
    try {
      if (sessionStorage.getItem('token') === null || sessionStorage.getItem('role') == null || sessionStorage.getItem('role') !== "admin") {
        alert("Access denied");
        this.router.navigate(["/"]);
        return;
      }
    } catch (error) {
      console.log(error);
    }

    const mp = (this.jwtservice.getPayload(sessionStorage.getItem("token")!));
    this.expired = new Date(mp["iat"] * 1000 + 30 * 60 * 1000);
    this.getAllUsers();
    this.getAnalytics();
    this.getAnalyticsbyuser();
    this.getAnalyticsbyexpert();
    this.getWalletAmtFromPaymentInfo();
  }

  getWalletAmtFromPaymentInfo(){
    
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.http.get<{"userid":string,"transactionId":string,"date":Date}[]>('http://localhost:9091/admin/wallet', { headers }).subscribe(
      (response) => {
        console.log(response);
        this.amountWallet=response;
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );

  }

  getAllUsers() {

    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.http.get<any[]>('http://localhost:9091/admin/allusers', { headers }).subscribe(
      (response) => {
        this.creds = response;
        // console.log(this.creds);
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }

  onNotifyParent1() {
    console.log(this.creds);
    let csvContent = 'Credentials ID,Username,Role\n';
    this.creds.forEach((item: any) => {
      csvContent += `${item.credentialsId},${item.username},${item.role}\n`;
    });
    console.log(csvContent);
    // Convert CSV content to a Blob
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });

    // Save the Blob as a file
    saveAs(blob, 'registered_user.csv');
  }
  onNotifyParent2() {
    console.log(" in 2");
    console.log(this.analyticsData3);
    let csvContent = 'Expert Id,Total Issues,Resolved Issues, Unresolved Issues\n';
    Object.entries(this.analyticsData3).map(([k, v]) => {
      csvContent+=k+","+(v["resolved"]+v["unresolved"])+","+(v["resolved"])+","+(v["unresolved"])+"\n";
    });
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    saveAs(blob, 'ExpertsReport.csv');
  }
  onNotifyParent3() {
    let csvContent = 'user Id,Total Issues,Resolved Issues, Unresolved Issues\n';
    Object.entries(this.analyticsData2).map(([k, v]) => {
      csvContent+=k+","+(v["total"])+","+v["resolved"]+","+(v["total"]-v["resolved"])+"\n";
    });
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    saveAs(blob, 'UsersReport.csv');
  }
  onNotifyParent4(){
    let csvContent = 'user Id,Transaction Id,Date, Price IN INR \n';
    this.amountWallet.forEach(
      payment=>{
        csvContent+=payment.userid+","+payment.transactionId+","+payment.date+","+(0.05*80)+"\n";
      }
    )
    csvContent+="Total,,,"+(this.amountWallet.length*(0.05*80));
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    saveAs(blob, 'Wallet.csv');
    console.log(csvContent);
  }

  deleteuser(_t13: number) {
    if (this.creds.at(_t13)?.role === "admin") {
      alert("Cannot delete admin")
      return;
    }
    const confirmation = confirm('Are you sure you want to delete ' + this.creds.at(_t13)?.username + '?');
    if (confirmation) {

      const token = sessionStorage.getItem('token');
      const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
      this.http.delete<any[]>('http://localhost:9091/admin/deleteuser/' + this.creds.at(_t13)?.credentialsId, { headers }).subscribe(
        (response) => {
          // console.log(response);
          this.creds.splice(_t13, 1);
        },
        (error) => {
          console.error('Error fetching users:', error);
        }
      );
      this.ngOnInit();
    }
  }

  getAnalytics() {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.http.get<any>('http://localhost:9091/admin/issues/analytics', { headers: headers }).subscribe(
      (response) => {
        this.analyticsData = response;
      },
      (error) => {
        console.error('Error fetching analytics data:', error);
      }
    );
  }
  getAnalyticsbyuser() {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.http.get<any>('http://localhost:9091/admin/issues/analytics/user', { headers: headers }).subscribe(
      (response) => {
        this.analyticsData2 = response;
        // console.log(response);
      },
      (error) => {
        console.error('Error fetching analytics data:', error);
      }
    );
  }

  getAnalyticsbyexpert() {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.http.get<any>('http://localhost:9091/admin/issues/analytics/expert', { headers: headers }).subscribe(
      (response) => {
        this.analyticsData3 = response;
        // console.log(response);
      },
      (error) => {
        console.error('Error fetching analytics data:', error);
      }
    );
  }


}
