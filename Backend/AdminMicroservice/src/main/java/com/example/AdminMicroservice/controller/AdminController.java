package com.example.AdminMicroservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminMicroservice.exception.AdminPreviligeException;
import com.example.AdminMicroservice.model.PaymentInfo;
import com.example.AdminMicroservice.model.credentials;
import com.example.AdminMicroservice.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
// TODO: Auto-generated Javadoc

/**
 * This is the controller class of admin role .
 *
 * @author Pushpraj singh pawar
 */

@RestController
@RequestMapping("/admin") 
public class AdminController {
	
	/** The admin service. */
	@Autowired
	public AdminService adminService;
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * This is the function to get the issue analytics  .
	 *
	 * @author Pushpraj singh pawar
	 * @return HashMap<String, Integer>
	 * @throws Exception the exception
	 * @category controller
	 */
	@GetMapping("/issues/analytics")
	@Operation(summary = "Get all resolved issue and unresolved issue count")
	public ResponseEntity<HashMap<String, Integer>> issueAnalytics() throws Exception{
		HashMap<String, Integer> mp=new HashMap<>();
		mp = adminService.issueAnalystics();
		logger.info(mp.entrySet().toString()); 
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mp);	
	}
	
	/**
	 * This is the function to get the issue analytics of all users.
	 *
	 * @author Pushpraj singh pawar
	 * @return Map<String, Map<String, Integer>>
	 * @throws Exception the exception
	 * @category controller
	 */
	@GetMapping("/issues/analytics/user")
	@Operation(summary = "Get all resolved issue and unresolved issue count per User Id")
	public ResponseEntity<Map<String, Map<String, Integer>>> issueAnalyticsForUser()throws Exception {
		Map<String, Map<String, Integer>> excelsheet = adminService.issueAnalysticsByUser();
	    logger.info("Returned issue analytics for user: {}", excelsheet);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminService.issueAnalysticsByUser()); 
	}
	
	
	/**
	 * This is the function to get the issue analytics of all experts.
	 *
	 * @author Pushpraj singh pawar
	 * @return Map<String, Map<String, Integer>>
	 * @throws Exception the exception
	 * @category controller
	 */
	@GetMapping("/issues/analytics/expert")
	@Operation(summary = "Get all resolved issue and unresolved issue count per Expert Id")
	public ResponseEntity<Map<String, Map<String, Integer>>> issueAnalyticsForExpert()throws Exception {
		Map<String, Map<String, Integer>> excelsheet = adminService.issueAnalysticsByExpert();
		logger.info("Returned issue analytics for user: {}", excelsheet);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(excelsheet);
	}
	
	
	/**
	 * This is the function to get all users registered as either expert or normal user does not give admin's data
	 * also hides user's password.
	 *
	 * @author Pushpraj singh pawar
	 * @return List<credentials>
	 * @throws Exception the exception
	 * @category controller
	 */
	@GetMapping("/allusers")
	@Operation(summary = "Get all users in the database Expert admin and Normal Users")
	public ResponseEntity<List<credentials>> alusers()throws Exception {
		List<credentials> allUsers = adminService.allusers();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allUsers);
	} 
	
	/**
	 * This is the function to delete a user from the system.
	 *
	 * @author Pushpraj singh pawar
	 * @param credentialsId the credentials id
	 * @return Boolean
	 * @throws AdminPreviligeException the admin previlige exception
	 * @throws Exception the exception
	 * @category controller
	 */
	@DeleteMapping("/deleteuser/{credentialsId}")
	@Operation(summary = "After Deleting the user return true if deleted else return false")
	public ResponseEntity<Boolean> deleteUser(@PathVariable String credentialsId) throws AdminPreviligeException, Exception {
			adminService.deleteUserByUserId(credentialsId); 
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
	} 
	
	@GetMapping("/wallet")
	@Operation(summary = "method will return the total payment done")
	public ResponseEntity<List<PaymentInfo>> getallpaidissues() {
		List<PaymentInfo> walletamt = adminService.wallet();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(walletamt);
	}
}