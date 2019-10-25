package com.sample.restapi.service;

import org.springframework.http.ResponseEntity;

public interface LinkedinEchoboxService {

	/**
	 * 
	 * @param code
	 * @param entityType
	 * @param id
	 * @return
	 */
	public ResponseEntity<String> createLinkedinShare(String code, String entityType, String id);

	/**
	 * 
	 * @param code
	 * @param organizationId
	 * @return
	 */
	public ResponseEntity<String> retrieveLinkedinOrganization(String code, String entityType, String id);

	/**
	 * 
	 * @param code
	 * @param entityType
	 * @param id
	 * @return
	 */
	public ResponseEntity<String> organizationConnectionExamples(String code, String entityType, String id);

}
