package com.example.IssueDataMicroservice.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.IssueDataMicroservice.model.Issue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IssueRepository.
 */
public interface IssueRepository extends MongoRepository<Issue, String>{
    
    /**
     * Find by userid.
     *
     * @param userId the user id
     * @return the list
     */
    List<Issue> findByUserid(String userId);

	/**
	 * Find by issueid.
	 *
	 * @param issueid the issueid
	 * @return the issue
	 */
	Issue findByIssueid(String issueid);

}
