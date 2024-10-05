package com.example.ExpertMicroservice.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ExpertMicroservice.model.Expert;


// TODO: Auto-generated Javadoc
/**
 * The Interface ExpertRepository.
 */
public interface ExpertRepository extends MongoRepository<Expert, String>{
    
    /**
     * Find byspecialist in in.
     *
     * @param specialist the specialist
     * @return the list
     */
    List<Expert> findByspecialistInIn(String specialist);

	boolean findByName(String name);
}
