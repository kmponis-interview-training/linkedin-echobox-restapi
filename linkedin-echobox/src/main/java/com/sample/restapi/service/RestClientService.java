package com.sample.restapi.service;

import org.springframework.http.ResponseEntity;

public interface RestClientService {

	/**
	 * Get responseEntity after get request
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> getRequest();

	/**
	 * Get responseEntity after get request with Authorization Token
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> getRequestWithAuthorizationToken();

	/**
	 * Get responseEntity after get request with Basic Authentication
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> getRequestWithBasicAuthentication();

	/**
	 * Get responseEntity after post request
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> postRequest();

	/**
	 * Get responseEntity after post request with Authorization Token
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> postRequestWithAuthorizationToken(String authToken);

}
