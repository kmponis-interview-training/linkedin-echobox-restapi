package com.sample.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.restapi.service.SampleService;

import io.swagger.annotations.ApiOperation;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;

	@ApiOperation(value = "Get the index page message.", response = ResponseEntity.class)
	@GetMapping(value = "/")
	public ResponseEntity<String> index() {
		return sampleService.getResponseIndex();
	}

}
