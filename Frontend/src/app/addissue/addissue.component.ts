import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, NgZone, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addissue',
  templateUrl: './addissue.component.html',
  styleUrl: './addissue.component.scss'
})
export class AddissueComponent {

   // issue:Issue=new Issue("","","","","",false,"",false,"");
   dtls:any={};
   showPaidDeatils=false;
   PaidIssue:boolean=false;
   PaymenterrorMessage:string="";
   @ViewChild('paymentRef', { static: true }) paymentRef!: ElementRef;
 
   constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { }
 
   issueAddForm: FormGroup = this.fb.group({
     softwareName: ['', Validators.required],
     softwareIssueTitle: ['', Validators.required],
     softwareIssueType: ['', Validators.required],
     softwareIssueDescription: ['', Validators.required]
   });
  specs: string[] = [];

  
  ngOnInit() {


    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    this.PaidIssue=false;
    this.http.get<string[]>("http://localhost:9091/user/getallspecializations", { headers }).subscribe(
      (response) => {
        this.specs = response;
        console.log(this.specs);
      },
      (error) => {
        console.log(error);
      }
    )

  }

 

  addissueFinal() {
    const formData = this.issueAddForm.value;

    console.log(formData.softwareName, formData.softwareIssueTitle, formData.softwareIssueType, formData.softwareIssueDescription);

    if (formData.softwareName == null || formData.softwareName === "") {
      alert('Choose software name');
      return;
    }
    if (formData.softwareIssueTitle == null || formData.softwareIssueTitle === "" || formData.softwareIssueTitle.length > 30) {
      alert('Enter Software Issue Title max size allowed = 30');
      return;
    }
    if (formData.softwareIssueType == null || formData.softwareIssueType === "" || formData.softwareIssueType.length > 30) {
      alert('Enter Software Issue Type max size allowed = 30');
      return;
    }
    if (formData.softwareIssueDescription == null || formData.softwareIssueDescription === "" || formData.softwareIssueDescription.length > 400) {
      alert('Enter Software Issue Description max size allowed = 400');
      return;
    }

    console.log(formData.softwareName, formData.softwareIssueTitle, formData.softwareIssueType, formData.softwareIssueDescription);



    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };


    this.http.post('http://localhost:9091/user/addissue', {
      softwareName: formData.softwareName,
      softwareIssueType: formData.softwareIssueType,
      softwareIssueTitle: formData.softwareIssueTitle,
      softwareIssueDescription: formData.softwareIssueDescription,
      paidTicket: this.PaidIssue
    }, { headers, responseType: 'text' }) // Set the responseType to 'text'
      .subscribe(
        (response) => {
          console.log('Response:', response);
          alert('Successfully added issue');
          this.router.navigate(['/userdashboard']);
        },
        (error) => {
          console.error('Error:', error);
          alert('Successfully added issue');
          this.router.navigate(['/userdashboard']);
        }
      );
  }

  savePaymentInfo(userid:string, tranxid:string){
    const token = sessionStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    this.http.post('http://localhost:9091/user/savepaymentinfo', {
      userid: userid,
      transactionId:tranxid,
    }, { headers, responseType: 'text' }) // Set the responseType to 'text'
      .subscribe(
        (response) => {
          console.log('Response:', response);
        },
        (error) => {
        }
      );
  }

  ngAfterViewInit() {
    console.log(window.paypal);
    window.paypal.Buttons(
      {
        createOrder: (data:any, actions:any) => {
          return actions.order.create({
            purchase_units: [{
              amount: {
                value: '0.05',
                currency_code: 'USD'
              }
            }]
          });
        },
        onApprove: (data:any, actions:any) => {
          return actions.order.capture().then((details:any) => {
            console.log(details);
            if(details.status==="COMPLETED"){

              this.dtls["status"]=details.status;
              this.dtls["transactinfo"]=details.id;
              this.showPaidDeatils=true;
              this.PaidIssue=true;
              this.savePaymentInfo(sessionStorage.getItem("credId")!, details.id);
            }
          });
        },
        onError:(error:any)=>{
          console.log(error);
          this.PaymenterrorMessage="Payment unsucessful please refill the form";
        }
      }
    ).render(this.paymentRef.nativeElement)
  }


}