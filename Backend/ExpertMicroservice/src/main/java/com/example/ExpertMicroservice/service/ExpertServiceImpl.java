package com.example.ExpertMicroservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExpertMicroservice.exception.ComesFromInvalidSourceException;
import com.example.ExpertMicroservice.externalservices.IssueDataMicroServices;
import com.example.ExpertMicroservice.externalservices.SecurtiyMicroserviceClient;
import com.example.ExpertMicroservice.model.Expert;
import com.example.ExpertMicroservice.model.Feedback;
import com.example.ExpertMicroservice.model.Issue;
import com.example.ExpertMicroservice.model.credentials;
import com.example.ExpertMicroservice.repository.ExpertRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpertServiceImpl.
 */
@Service
public class ExpertServiceImpl implements ExpertService {

	/** The expert repository. */
	@Autowired
	ExpertRepository expertRepository;

	/** The issue data micro services. */
	@Autowired
	IssueDataMicroServices issueDataMicroServices;

	/** The ab. */
	@Autowired
	SecurtiyMicroserviceClient ab;

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(ExpertService.class);

	/**
	 * Adds the expert.
	 *
	 * @param expert the expert
	 * @return the expert
	 * @throws Exception the exception
	 */
	public Expert addExpert(Expert expert) throws Exception {
//		if (expertRepository.findById(expert.getExpertid()).isPresent()) {
//			logger.error("Expert Already Exists with expertid " + expert.getExpertid());
//			throw new Exception("Expert ALready Exists with expertid " + expert.getExpertid());
//		}
//		if(expertRepository.findByName(expert.getName())) {
//			throw new Exception("Expert WITH "+expert.getName()+"  already  exists");
//		}
		expert.setIssuesids(Arrays.asList());
		expert.setFeedbackids(Arrays.asList());
		expert.setRoles(Arrays.asList("expert"));
		ab.addUser(new credentials(expert.getExpertid(), expert.getName(), expert.getPassword(),
				expert.getRoles().get(0)));
		logger.info("credentials created with expertid " + expert.getExpertid());
		List<String> spcs = expert.getSpecialistIn().stream().map(str -> str.toLowerCase().trim())
				.collect(Collectors.toList());
		expert.setSpecialistIn(spcs);
		return expertRepository.save(expert);
	}

	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean addissue(Issue issue) throws Exception {
		List<Expert> experts = expertRepository.findByspecialistInIn(issue.getSoftwareName().toLowerCase());
		Expert expert = experts.get(0);
		for(Expert temp:experts) {
			if(temp.getIssuesids().size()<expert.getIssuesids().size()) {
				expert = temp;
			}
		}
		int len = 0;
		if (expert.getIssuesids() == null) {
			len = 0;
		} else {
			len = expert.getIssuesids().size();
		}

		if (expert.getIssuesids() == null) {
			expert.setIssuesids(Arrays.asList());
		}
		if (expert.getFeedbackids() == null) {
			expert.setFeedbackids(Arrays.asList());
		}
		// Making Composite keys for Issue document.
		issue.setIssueid(
				expert.getExpertid() + "-" + issue.getUserid() + "-" + (len + 1) + "-" + issue.getSoftwareIssueTitle());
		Issue issue2 = issueDataMicroServices.addIssue(issue);

		logger.info("Issue Added With Issue Id " + issue2.getIssueid());

		expert.getIssuesids().add(issue2.getIssueid());
		expert.getFeedbackids().add("fb" + issue2.getIssueid());
		logger.info("Feedback Added With Feedback Id " + expert.getExpertid());
		return expertRepository.save(expert) != null;
	}
 
	/**
	 * Gets the all feedbacks by expert id.
	 *
	 * @param expertid the expertid
	 * @return the all feedbacks by expert id
	 * @throws Exception the exception
	 */
	public List<Feedback> getAllFeedbacksByExpertId(String expertid) throws Exception {
		List<Feedback> feedbacks = issueDataMicroServices.getFeedbacksByExpertid(expertid);
		if (feedbacks.isEmpty() || feedbacks == null) {
			logger.error("No feedbacks curently");
			throw new Exception("NO feedbacks currently");
		}
		return feedbacks;
	}

	/**
	 * Editfeedback.
	 *
	 * @param issueid             the issueid
	 * @param feedbackDescription the feedback description
	 * @param expertid            the expertid
	 * @return the feedback
	 * @throws Exception the exception
	 */
	public Feedback editfeedback(String issueid, String feedbackDescription, String expertid) throws Exception {
		Optional<Expert> expert = expertRepository.findById(expertid);
		if (expert.isPresent() == false || expert == null) {
			throw new ComesFromInvalidSourceException("Comes From Invalid Source Exception");
		}
		return issueDataMicroServices.editfeedback("fb" + issueid, feedbackDescription);
	}

	/**
	 * Gets the issue by expert id.
	 *
	 * @param expertid the expertid
	 * @return the issue by expert id
	 * @throws Exception the exception
	 */
	public List<Issue> getissueByExpertId(String expertid) throws Exception {
		return issueDataMicroServices.getIssuesByExpertId(expertid);
	}

	/**
	 * Gets the resolved issue.
	 *
	 * @param expertid the expertid
	 * @return the resolved issue
	 * @throws Exception the exception
	 */
	public List<Issue> getResolvedIssue(String expertid) throws Exception {
		List<Issue> issues = issueDataMicroServices.getIssuesByExpertId(expertid);
		issues = issues.stream().filter(issue -> issue.getStatus().equalsIgnoreCase("resolved"))
				.collect(Collectors.toList());
		return issues;
	}

	/**
	 * Gets the un resolved issue.
	 *
	 * @param expertid the expertid
	 * @return the un resolved issue
	 * @throws Exception the exception
	 */
	public List<Issue> getUnResolvedIssue(String expertid) throws Exception {
		List<Issue> issues = issueDataMicroServices.getIssuesByExpertId(expertid);
		issues = issues.stream().filter(issue -> issue.getStatus().equalsIgnoreCase("unresolved"))
				.collect(Collectors.toList());
		return issues;
	}

	/**
	 * Gets the allspecs.
	 *
	 * @return the allspecs
	 */
	@Override
	public List<String> getallspecs() {
		List<String> specsAll = Arrays.asList();
		List<Expert> experts = expertRepository.findAll();
		HashSet<String> distinct = new HashSet<>();
		for(Expert temp:experts) {
			distinct.addAll(temp.getSpecialistIn());
		}
		specsAll = distinct.stream().collect(Collectors.toList());
		return specsAll;
	}

	@Override
	public Expert getExpertById(String expertid) {
		// TODO Auto-generated method stub
		return expertRepository.findById(expertid).get();
	}
}
