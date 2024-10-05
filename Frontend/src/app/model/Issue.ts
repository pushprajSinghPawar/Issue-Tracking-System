export class Issue {
    issueid: string;
    softwareName: string;
    softwareIssueType: string;
    softwareIssueTitle: string;
    softwareIssueDescription: string;
    highPriority: boolean;
    status: string;
    paidTicket: boolean;
    userid: string;
    dateissueform: Date;
  
    constructor(issueid: string, softwareName: string, softwareIssueType: string, softwareIssueTitle: string,
      softwareIssueDescription: string, highPriority: boolean, status: string, paidTicket: boolean, userid: string) {
      this.issueid = issueid;
      this.softwareName = softwareName;
      this.softwareIssueType = softwareIssueType;
      this.softwareIssueTitle = softwareIssueTitle;
      this.softwareIssueDescription = softwareIssueDescription;
      this.highPriority = highPriority;
      this.status = status;
      this.paidTicket = paidTicket;
      this.userid = userid;
      this.dateissueform = new Date();
    }
  
    toString(): string {
      return `Issue [issueid=${this.issueid}, softwareName=${this.softwareName}, softwareIssueType=${this.softwareIssueType}, softwareIssueTitle=${this.softwareIssueTitle}, softwareIssueDescription=${this.softwareIssueDescription}, highPriority=${this.highPriority}, status=${this.status}, paidTicket=${this.paidTicket}, userid=${this.userid}, dateissueform=${this.dateissueform}]`;
    }
  }
  