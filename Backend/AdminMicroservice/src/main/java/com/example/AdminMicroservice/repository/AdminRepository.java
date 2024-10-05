package com.example.AdminMicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.AdminMicroservice.model.credentials;

/**
 * The Interface AdminRepository.
 *
 * @author pushpraj singh pawar
 * used by the service classes to fetch the data of credentials document in the database
 * @category repository
 */
public interface AdminRepository extends MongoRepository<credentials, String>{
	
}