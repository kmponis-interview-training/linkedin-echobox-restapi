package com.sample.restapi.service;

import org.springframework.http.ResponseEntity;

public interface SampleService {

	/**
	 * Get responseEntity 'Index Page!'
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> getResponseIndex();

}
