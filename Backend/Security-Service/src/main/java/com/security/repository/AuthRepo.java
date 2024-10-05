package com.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.security.model.credentials;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthRepo.
 */
@Repository
public interface AuthRepo extends MongoRepository<credentials, String>{

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	Optional<credentials> findByUsername(String username);

}
