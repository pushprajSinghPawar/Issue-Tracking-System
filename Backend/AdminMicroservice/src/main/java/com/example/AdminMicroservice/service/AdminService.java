package com.example.AdminMicroservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.AdminMicroservice.exception.AdminPreviligeException;
import com.example.AdminMicroservice.model.Expert;
import com.example.AdminMicroservice.model.PaymentInfo;
import com.example.AdminMicroservice.model.User;
import com.example.AdminMicroservice.model.credentials;
// TODO: Auto-generated Javadoc

/**
 * The Interface AdminService.
 *
 * @author pushpraj singh pawar
 * used by the controller classes to fetch the data of credentials document in the database
 * @category service
 */
public interface AdminService {
	
	/**
	 * Issue analystics.
	 *
	 * @return the hash map
	 * @throws Exception the exception
	 */
	public HashMap<String, Integer> issueAnalystics()throws Exception;
	
	/**
	 * Issue analystics by user.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	public Map<String, Map<String, Integer>> issueAnalysticsByUser()throws Exception;
	
	/**
	 * Issue analystics by expert.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	public Map<String, Map<String, Integer>> issueAnalysticsByExpert() throws Exception;
	
	/**
	 * Allusers.
	 *
	 * @return the list
	 */
	public List<credentials> allusers();
	
	/**
	 * Delete user by user id.
	 *
	 * @param credentialsId the credentials id
	 * @throws AdminPreviligeException the admin previlige exception
	 * @throws Exception the exception
	 */
	public void deleteUserByUserId(String credentialsId) throws AdminPreviligeException, Exception;

	public List<PaymentInfo> wallet();

}
