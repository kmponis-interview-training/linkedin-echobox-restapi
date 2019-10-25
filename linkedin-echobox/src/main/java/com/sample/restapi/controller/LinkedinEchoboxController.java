package com.sample.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.restapi.service.LinkedinEchoboxService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/linkedinEchobox/api/v1")
public class LinkedinEchoboxController {

	@Autowired
	private LinkedinEchoboxService linkedinEchoboxService;

	@ApiOperation(value = "To create a LinkedIn Share.", response = ResponseEntity.class)
	@PostMapping(value = "/createLinkedinShare")
	public ResponseEntity<String> createLinkedinShare(@RequestParam(value = "code") String code,
			@RequestParam(value = "entityType") String entityType, @RequestParam(value = "id") String id) {
		return linkedinEchoboxService.createLinkedinShare(code, entityType, id);
	}

	@ApiOperation(value = "To retrieve an organization from LinkedIn.", response = ResponseEntity.class)
	@PostMapping(value = "/retrieveLinkedinOrganization")
	public ResponseEntity<String> retrieveLinkedinOrganization(@RequestParam(value = "code") String code,
			@RequestParam(value = "entityType") String entityType, @RequestParam(value = "id") String id) {
		return linkedinEchoboxService.retrieveLinkedinOrganization(code, entityType, id);
	}

	@ApiOperation(value = "Using organizationConnection usefull examples.", response = ResponseEntity.class)
	@PostMapping(value = "/organizationConnectionExamples")
	public ResponseEntity<String> organizationConnectionExamples(@RequestParam(value = "code") String code,
			@RequestParam(value = "entityType") String entityType, @RequestParam(value = "id") String id) {
		return linkedinEchoboxService.organizationConnectionExamples(code, entityType, id);
	}

}
