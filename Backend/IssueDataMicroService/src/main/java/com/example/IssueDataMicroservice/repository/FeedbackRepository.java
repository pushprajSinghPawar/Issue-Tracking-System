package com.example.IssueDataMicroservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.IssueDataMicroservice.model.Feedback;

// TODO: Auto-generated Javadoc
/**
 * The Interface FeedbackRepository.
 */
public interface FeedbackRepository extends  MongoRepository<Feedback, String>{

	/**
	 * Find by expertid.
	 *
	 * @param expertid the expertid
	 * @return the list
	 */
	List<Feedback> findByExpertid(String expertid);

}
