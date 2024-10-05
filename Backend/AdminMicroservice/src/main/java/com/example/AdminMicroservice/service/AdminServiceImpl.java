package com.example.AdminMicroservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AdminMicroservice.exception.AdminPreviligeException;
import com.example.AdminMicroservice.externalservice.ExpertMicroServiceClient;
import com.example.AdminMicroservice.externalservice.IssueMicroServiceClient;
import com.example.AdminMicroservice.externalservice.UserMicroServiceClient;
import com.example.AdminMicroservice.model.Expert;
import com.example.AdminMicroservice.model.Feedback;
import com.example.AdminMicroservice.model.Issue;
import com.example.AdminMicroservice.model.PaymentInfo;
import com.example.AdminMicroservice.model.User;
import com.example.AdminMicroservice.model.credentials;
import com.example.AdminMicroservice.repository.AdminRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminServiceImpl.
 *
 * @author pushpraj singh pawar
 * used by the controller classes to fetch the data of credentials document in the database
 * @category service
 */
@Service
public class AdminServiceImpl implements AdminService{

	
	/** The expert micro service client. */
	@Autowired ExpertMicroServiceClient expertMicroServiceClient;
	
	/** The user micro service client. */
	@Autowired UserMicroServiceClient userMicroServiceClient;
	
	/** The issue micro service client. */
	@Autowired IssueMicroServiceClient issueMicroServiceClient;
	
	/** The admin repository. */
	@Autowired AdminRepository adminRepository;
	
	
	/**
	 * get the issues and segregate the resolved and unresolved issues and return count of them to controller method.
	 *
	 * @return HashMap<String, Integer>
	 * @throws Exception the exception
	 */
	public HashMap<String, Integer> issueAnalystics()throws Exception{
		HashMap<String, Integer> mp= new HashMap<>();
		mp.put("RESOLVED",0);
		mp.put("UNRESOLVED",0);
		List<Issue> issues= issueMicroServiceClient.getallissue();
		if(issues.isEmpty() || issues==null) {
			return mp;
		}
		issues.stream().forEach(
				issue->{
					if(issue.getStatus().equalsIgnoreCase("resolved")) {
						mp.put("RESOLVED", mp.get("RESOLVED")+1);
					}else{
						mp.put("UNRESOLVED", mp.get("UNRESOLVED")+1);
					}
				}
		);
		return mp;
	}

	
	/**
	 * get the issues and return count each users' resolved and unresolved issues of them to controller method.
	 *
	 * @return Map<String, Map<String, Integer>>
	 * @throws Exception the exception
	 */
	@Override
	public Map<String, Map<String, Integer>> issueAnalysticsByUser() throws Exception {
	    List<Issue> issues = issueMicroServiceClient.getallissue();

	    Map<String, Map<String, Integer>> userMap = new HashMap<>();
	    if (issues == null || issues.isEmpty()) {
	        return userMap;
	    }

	    for (Issue issue : issues) {
	        String userId = issue.getUserid();
	        userMap.putIfAbsent(userId, new HashMap<>());
	        Map<String, Integer> userStats = userMap.get(userId);
	        userStats.put("total", userStats.getOrDefault("total", 0) + 1);

	        if (issue.getStatus().equals("resolved")) {
	            userStats.put("resolved", userStats.getOrDefault("resolved", 0) + 1);
	        }else {
	        	userStats.put("resolved", userStats.getOrDefault("resolved", 0));
	        }
	    }

	    return userMap;
	}
	
	/**
	 * get the issues and return count each expert's resolved and unresolved issues of them to controller method.
	 *
	 * @return Map<String, Map<String, Integer>>
	 * @throws Exception the exception
	 */
	@Override
	public Map<String, Map<String, Integer>> issueAnalysticsByExpert() throws Exception{
	    // Map to store expert ID and their resolved/unresolved issues count
	    Map<String, Map<String, Integer>> expertMap = new HashMap<>();
	    List<Feedback> feedbacks = issueMicroServiceClient.getallFeedbacks();
	    if (feedbacks.isEmpty() || feedbacks == null) {
	        return expertMap;
	    }


	    for (Feedback feedback : feedbacks) {
	        String expertId = feedback.getExpertid();
	        boolean resolved = !feedback.getFeedbackDescription().equalsIgnoreCase("");

	        // Get or create the expert's map
	        HashMap<String, Integer> expertData = (HashMap<String, Integer>) expertMap.computeIfAbsent(expertId, k -> new HashMap<>());

	        // Update the resolved/unresolved count for the expert
	        expertData.put("resolved", expertData.getOrDefault("resolved", 0) + (resolved ? 1 : 0));
	        expertData.put("unresolved", expertData.getOrDefault("unresolved", 0) + (resolved ? 0 : 1));
	    }

	    return expertMap;
	}
	
	
	/**
	 * get the list of all registered users in he system.
	 *
	 * @return List<credentials>
	 */
	@Override
	public List<credentials> allusers() {
		// TODO Auto-generated method stub
		List<credentials> creds = adminRepository.findAll();
		creds = creds.stream().filter(cred->!cred.getRole().equals("admin")).collect(Collectors.toList());
		return creds;
	}
	
	/**
	 * deletes  the user's credentials based on its id.
	 *
	 * @param credentialsId the credentials id
	 * @return void
	 * @throws AdminPreviligeException if credential id is of Admin it throws AdminPreviligeException exception
	 * if  credential id is not found it just returns without deleting
	 * if it finds the user with credential id it deletes it from the credential document.
	 * @throws Exception the exception
	 */
	@Override
	public void deleteUserByUserId(String credentialsId) throws AdminPreviligeException, Exception {
		// TODO Auto-generated method stub
		Optional<credentials> credential = adminRepository.findById(credentialsId);
		System.out.println(credential.get());
		if(credential.isPresent()) {
			if(credential.get().getRole().equals("admin")) {
				throw new AdminPreviligeException("Cannot Delete Admin");
			}else {
				adminRepository.deleteById(credentialsId);
			}
		}
		return ;
	}

	
	/**
	 * Get the amount accumulated by the pad issues.
	 *
	 */
	@Override
	public List<PaymentInfo> wallet() {
		// TODO Auto-generated method stub
		return  (userMicroServiceClient.findpaidIssues());
	}

}
