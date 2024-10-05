package com.example.UserMicroservice.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.UserMicroservice.model.Feedback;
import com.example.UserMicroservice.model.Issue;

import io.swagger.v3.oas.annotations.Operation;

// TODO: Auto-generated Javadoc
/**
 * The Interface IssueMicroServiceClient.
 */
@FeignClient(name = "IssueDataMicroService", url = "http://localhost:8083") // Assuming the URL of the IssueDataMicroservice
public interface IssueMicroServiceClient {

    /**
     * Creates the issue.
     *
     * @param issue the issue
     * @return the issue
     */
    @PostMapping("/issue/add")
    Issue createIssue(@RequestBody Issue issue);
    
    /**
     * Gets the allissue.
     *
     * @param userid the userid
     * @return the allissue
     */
    @GetMapping("/issue/getall/{userid}")
    List<Issue> getallissue(@PathVariable String userid);
    
    /**
     * Gets the feedbackbyid.
     *
     * @param feedbackid the feedbackid
     * @return the feedbackbyid
     */
    @GetMapping("/feedback/{feedbackid}")
    Feedback getfeedbackbyid(@PathVariable String feedbackid);
    
    /**
     * edit issue by the issueid.
     *
     * @param issueid the issueid
     * @return the Issue
     */
    @PutMapping("/issue/edit/{issueid}")
	public Issue editIssue(@PathVariable String issueid,@RequestBody String issuedescription);
    

}
