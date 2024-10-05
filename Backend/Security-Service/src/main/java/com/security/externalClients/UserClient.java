package com.security.externalClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.security.model.User;


// TODO: Auto-generated Javadoc
/**
 * The Interface UserClient.
 */
@FeignClient(name = "UserMicroservice", url = "http://localhost:8080")
public interface UserClient {
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@PostMapping("/user/register")
	User addUser(@RequestBody User user);
}
