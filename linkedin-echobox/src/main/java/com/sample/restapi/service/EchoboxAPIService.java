package com.sample.restapi.service;

import org.springframework.http.ResponseEntity;

public interface EchoboxAPIService {

	/**
	 * 
	 * @param authToken
	 * @param userStateId
	 * @param permissions
	 * @return
	 */
	public ResponseEntity<String> getApiEchoboxUsers(String authToken, String userStateId, String permissions);

	/**
	 * 
	 * @param authToken
	 * @param propertyId
	 * @param propertyStateId
	 * @param propertyTypeId
	 * @return
	 */
	public ResponseEntity<String> getApiEchoboxProperties(String authToken, String propertyId, String propertyStateId,
			String propertyTypeId);

}
