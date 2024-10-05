package com.example.UserMicroservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.UserMicroservice.model.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends MongoRepository<User, String>{

	boolean findByName(String name);
}
