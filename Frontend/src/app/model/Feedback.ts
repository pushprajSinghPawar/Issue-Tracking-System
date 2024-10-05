export class Feedback {
    feedbackid: string;
    userid: string;
    issueid: string;
    expertid: string;
    feedbackDescription: string;
    datefeedbackform: Date;
  
    constructor(feedbackid: string, userid: string, issueid: string, expertid: string, feedbackDescription: string) {
      this.feedbackid = feedbackid;
      this.userid = userid;
      this.issueid = issueid;
      this.expertid = expertid;
      this.feedbackDescription = feedbackDescription;
      this.datefeedbackform = new Date();
    }
  }
  