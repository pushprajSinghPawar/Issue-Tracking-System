package com.example.AdminMicroservice.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.AdminMicroservice.externalservice.ExpertMicroServiceClient;
import com.example.AdminMicroservice.externalservice.IssueMicroServiceClient;
import com.example.AdminMicroservice.externalservice.UserMicroServiceClient;
import com.example.AdminMicroservice.model.Feedback;
import com.example.AdminMicroservice.model.Issue;
import com.example.AdminMicroservice.model.credentials;
import com.example.AdminMicroservice.repository.AdminRepository;
import com.example.AdminMicroservice.service.AdminService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	AdminService adminService;
    /** The expert micro service client. */
    @MockBean
    private ExpertMicroServiceClient expertMicroServiceClient;

    /** The user micro service client. */
    @MockBean
    private UserMicroServiceClient userMicroServiceClient;

    /** The issue micro service client. */
    @MockBean
    private IssueMicroServiceClient issueMicroServiceClient;

    /** The admin repository. */
    @MockBean
    private AdminRepository adminRepository;
    
    @Test
    void testgetAllUsers() {
    	List<credentials> creds =Arrays.asList(new credentials("1id","1name","1password","user"));
    	when(adminRepository.findAll()).thenReturn(creds);
    	assertEquals(adminService.allusers(), creds);
    }
    @Test
    public void testIssueAnalystics() throws Exception {
        List<Issue> issues = new ArrayList();
        issues.add(new Issue("1issue", "resolved", null, null, null, false, "resolved", false, "1"));
        issues.add(new Issue("2issue", "unresolved", null, null, null, false, "unresolved", false, "1"));

        when(issueMicroServiceClient.getallissue()).thenReturn(issues);

        HashMap<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("RESOLVED", 1);
        expectedMap.put("UNRESOLVED", 1);

        assertEquals(expectedMap, adminService.issueAnalystics());
    }

    @Test
    public void testIssueAnalysticsByUser() throws Exception {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue("1", "resolved", null, null, null, false, "resolved", false, "1"));
        issues.add(new Issue("1", "unresolved", null, null, null, false,  "unresolved", false, "1"));

        when(issueMicroServiceClient.getallissue()).thenReturn(issues);

        Map<String, Map<String, Integer>> expectedMap = new HashMap<>();
        Map<String, Integer> user1Stats = new HashMap<>();
        user1Stats.put("total", 2);
        user1Stats.put("resolved", 1);
        expectedMap.put("1", user1Stats);

        assertEquals(expectedMap, adminService.issueAnalysticsByUser());
    }

    @Test
    public void testIssueAnalysticsByExpert() throws Exception {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(new Feedback("1", "feedbackDescription1", null, "1", "given feedback"));
        feedbacks.add(new Feedback("2", "", null, "1", ""));

        when(issueMicroServiceClient.getallFeedbacks()).thenReturn(feedbacks);

        Map<String, Map<String, Integer>> expectedMap = new HashMap<>();
        Map<String, Integer> expert1Stats = new HashMap();
        expert1Stats.put("resolved", 1);
        expert1Stats.put("unresolved", 1);
        expectedMap.put("1", expert1Stats);

        assertEquals(expectedMap, adminService.issueAnalysticsByExpert());
    }
}
