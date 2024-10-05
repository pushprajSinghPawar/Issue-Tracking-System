import { Component, NgZone } from '@angular/core';
import { Issue } from '../model/Issue';
import { HttpClient } from '@angular/common/http';
import { Feedback } from '../model/Feedback';
import { Router } from '@angular/router';
import { JwtService } from '../jwt-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrl: './userdashboard.component.css'
})
export class UserdashboardComponent {
  viewAllIssues() {
    this.allresolvedUnresolved = true;
    this.resolvedOnly = true;
    this.unresolvedOnly = true;
  }
  viewunresolved() {
    this.unresolvedOnly = true;
    this.allresolvedUnresolved = false;
    this.resolvedOnly = false;
  }
  viewresolved() {

    this.unresolvedOnly = false;
    this.allresolvedUnresolved = false;
    this.resolvedOnly = true;

  }


  sortAscendingDates() {
    this.zone.run(() => {
      this.issues.sort(
        (a, b) => new Date(a.dateissueform).getTime() - new Date(b.dateissueform).getTime()
      );
    });
  }

  sortDescendingDates() {
    this.zone.run(() => {
      this.issues.sort(
        (a, b) => new Date(b.dateissueform).getTime() - new Date(a.dateissueform).getTime()
      );
    });
  }

  IssueForm: FormGroup = this.fb.group({
    issueDescription: ['', Validators.required]
  });
  changedatefomat(dt: Date): string {
    var dt = new Date(dt);
    var temp: string = dt.toLocaleString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' });
    temp = temp.replace(",", " ");
    return temp;
  }



  editIssueInDb(issueid: string) {

    const formData = this.IssueForm.value;
    if (formData.issueDescription === null || formData.issueDescription === '' || formData.issueDescription.length > 400) {
      window.alert("issue Description cannot be more than 400 or empty");
      return;
    }

    const ids = this.IssueForm.value.issueDescription;


    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };

    this.http.put<any>('http://localhost:9091/user/editissue/' + issueid, ids, { headers }).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )

    this.IssueForm.reset();
    this.showIssueEditForm = !this.showIssueEditForm;
    window.location.reload();


  }
  issues: Issue[] = [];
  unrslvd: number = 0;
  rslvd: number = 0;
  feedback: Feedback = new Feedback("", "", "", "", "");
  showfeedback: boolean = false;

  resolvedOnly: boolean = true;
  unresolvedOnly: boolean = true;
  allresolvedUnresolved: boolean = true;
  clock: number = 0;
  expired: Date = new Date();
  PageNumber = 0;
  constructor(private http: HttpClient, private zone: NgZone, private router: Router, private jwtService: JwtService, private fb: FormBuilder) {

  }
  PageNumberinc() {
    this.PageNumber++;
    this.getAllIssues();
  }
  PageNumberdec() {
    this.PageNumber--;
    this.getAllIssues();
  }
  ngOnInit() {
    this.getAllIssues();
  }

  getAllIssues() {
    try {
      if (sessionStorage.getItem('token') === null || sessionStorage.getItem('role') == null || sessionStorage.getItem('role') !== "user") {
        alert("Access denied");
        this.router.navigate(["/"]);
        return;
      }
    } catch (error) {
      console.log(error);
    }

    // this.clock = sessionStorage.getItem("iat")+30*60*100;
    var mp = (this.jwtService.getPayload(sessionStorage.getItem("token")!));
    this.expired = new Date(mp["iat"] * 1000 + 30 * 60 * 1000);
    console.log(this.expired);
    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    const pagesize = 5;
    this.http.get<Issue[]>('http://localhost:9091/user/issues/' + this.PageNumber + '/' + pagesize, { headers }).subscribe(
      (response) => {
        this.issues = response;
        this.issues.forEach(is => {
          if (is.status == 'resolved') {
            this.rslvd++;
          } else {
            this.unrslvd++;
          }
        })

        console.log('Issues successfully fetched', this.issues);
      },
      (error) => {
        console.error('Error fetching issues', error);
      }
    );
  }
  currentIssue: Issue = new Issue("", "", "", "", "", false, "", false, "");

  viewfeedback(arg0: string, arg1: Issue) {
    const fid = 'fb' + arg0;
    this.currentIssue = arg1;
    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    this.http.get<Feedback>('http://localhost:9091/user/feedback/' + fid, { headers }).subscribe(
      (response) => {
        this.feedback = response;
        console.log('Issues successfully fetched', this.feedback);
      },
      (error) => {
        console.error('Error fetching issues', error);
      }
    );
    this.showfeedback = true;
  }

  showIssueEditForm = false;

  editIssue(iss: Issue) {
    this.showIssueEditForm = !this.showIssueEditForm;
    this.currentIssue = iss;
  }

}
