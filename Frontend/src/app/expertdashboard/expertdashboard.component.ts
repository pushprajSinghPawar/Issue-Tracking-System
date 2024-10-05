import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { Issue } from '../model/Issue';
import { Feedback } from '../model/Feedback';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JwtService } from '../jwt-service.service';

@Component({
  selector: 'app-expertdashboard',
  templateUrl: './expertdashboard.component.html',
  styleUrl: './expertdashboard.component.scss'
})
export class ExpertdashboardComponent {

  unrslvd:number=0;
  rslvd:number=0;
  totalIssues:number=0;
  editfeedbackmode: boolean = false;
  showfeedback: boolean = false;
  feedback: Feedback = new Feedback("", "", "", "", "");
  issue: Issue = new Issue("","","","","",false,"",false,"");
  feedbacks: Feedback[] = [];
  viewfeedbackmode: boolean = false;
  issues: Issue[] = [];
  allissues = true;
  resolvedOnly: boolean = true;
  unresolvedOnly: boolean = true;
  expired:Date=new Date();
  
  feedbackForm: FormGroup = this.fb.group({
    feedbackDescription: ['', Validators.required]
  });

  viewAllIssues() {
    this.allissues = true;
  }
  viewunresolved() {
    this.allissues = false;
    this.unresolvedOnly = true;
    this.resolvedOnly = false;
  }
  viewresolved() {
    this.allissues = false;
    this.unresolvedOnly = false;
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


  
  constructor(private http: HttpClient, private router: Router, private zone: NgZone, private fb: FormBuilder, private jwtservice:JwtService) {

  }

  ngOnInit() {
    // console.log("I am here");
    try{
      if(sessionStorage.getItem('token')===null || sessionStorage.getItem('role')==null || sessionStorage.getItem('role')!=="expert" ){
        alert("Access denied");
        this.router.navigate(["/"]);
        return ;
      }
    }catch(error){
      console.log(error);
    }
    
    var mp = (this.jwtservice.getPayload(sessionStorage.getItem("token")!));
    this.expired = new Date(mp["iat"]*1000 + 30*60*1000);
    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    // if(this.jwtservice.getPayload())
    this.http.get<Issue[]>('http://localhost:9091/expert/getissues', { headers }).subscribe(
      (response) => {
        this.issues = response;
        // console.log('Issues successfully fetched', this.issues);
        this.totalIssues = response.length;
        this.issues.forEach(is=>{
          if(is.status=='resolved'){
            this.rslvd++;
          }else{
            this.unrslvd++;
          }
        })
      },
      (error) => {
        console.error('Error fetching issues', error);
      }
    );
    this.http.get<Feedback[]>('http://localhost:9091/expert/getfeedbacks', { headers }).subscribe(
      (response) => {
        this.feedbacks = response;
        // console.log(this.feedbacks);
      },
      (error) => {
        // console.log(error);
        alert("no feedbacks yet");
      }
    );
  }

  updatefeedback(arg0: string, arg1:Issue) {
    this.feedback = this.feedbacks.filter(i => i.feedbackid === 'fb' + arg0).at(0)!;
    this.issue = arg1;
    this.viewfeedbackmode = true;
    this.editfeedbackmode = true;
  }
  viewfeedback(arg0: string, arg1:Issue) {
    this.feedback = this.feedbacks.filter(i => i.feedbackid === 'fb' + arg0).at(0)!;
    this.issue = arg1;
    this.viewfeedbackmode = true;
    this.editfeedbackmode = false;
  }
  closefeedback() {
    this.viewfeedbackmode = false;
    this.editfeedbackmode = false;
  }

  changedatefomat(dt: Date): string {
    var dt = new Date(dt);
    var temp:string =  dt.toLocaleString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' });
    temp=temp.replace(",", " ");
    return temp;
  }

  editFeedbackInDb(issueId: string) {
    // console.log("came here");
    const feedbackDescription = this.feedbackForm.value.feedbackDescription;
    // console.log(feedbackDescription);
    if (feedbackDescription === null || feedbackDescription.length < 3 || feedbackDescription.length > 400) {
      window.alert("feedback description cannot be less than 3 words more than 400 words");
      return;
    }

    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.http.put('http://localhost:9091/expert/editfeedback/' + issueId , {"feedbackDescription":feedbackDescription}, { headers })
      .subscribe(
        (response) => {
          // console.log(response);
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
          window.alert("some error occured");
          return ;
        }
      );
      this.viewfeedbackmode=false;
      this.feedbackForm.reset();
      window.location.reload();
  }


}
