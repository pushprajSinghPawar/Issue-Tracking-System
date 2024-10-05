package com.example.AdminMicroservice.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AdminMicroservice.model.PaymentInfo;
import com.example.AdminMicroservice.model.User;

/**
 * The Interface UserMicroServiceClient.
 *
 * @category feign client external service
 * request for response from the issue data micro-service
 */
@FeignClient(name = "UserMicroservice", url = "http://localhost:8080")
public interface UserMicroServiceClient {
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 * @category method
	 * calls the user micro-service to add the user type of user in the system
	 * it is controller method of  user micro-service
	 */
	@PostMapping("/user/register")
	User addUser(@RequestBody User user);

	@GetMapping("/user/getallpaidissues")
	List<PaymentInfo> findpaidIssues();
}
