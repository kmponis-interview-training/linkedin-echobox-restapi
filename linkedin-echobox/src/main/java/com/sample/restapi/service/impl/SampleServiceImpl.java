package com.sample.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.restapi.properties.ResponseProperties;
import com.sample.restapi.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private ResponseProperties responseProperties;

	@Override
	public ResponseEntity<String> getResponseIndex() {
		return ResponseEntity.status(HttpStatus.OK).body(responseProperties.index);
	}

}
