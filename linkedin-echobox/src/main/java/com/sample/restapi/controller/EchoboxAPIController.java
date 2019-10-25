package com.sample.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.restapi.service.EchoboxAPIService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/echobox/api/v1")
public class EchoboxAPIController {

	@Autowired
	private EchoboxAPIService echoboxAPIService;

	@ApiOperation(value = "Make getRequest to 'https://api.echobox.com/v3/users'", response = ResponseEntity.class)
	@GetMapping(value = "/getApiEchoboxUsers")
	public ResponseEntity<String> getApiEchoboxUsers(@RequestParam(value = "authToken") String authToken,
			@RequestParam(value = "userStateId") String userStateId,
			@RequestParam(value = "permissions") String permissions) {
		return echoboxAPIService.getApiEchoboxUsers(authToken, userStateId, permissions);
	}

	@ApiOperation(value = "Make getRequest to 'https:// api.echobox.com/v3/properties/propertyId'", response = ResponseEntity.class)
	@GetMapping(value = "/getApiEchoboxProperties")
	public ResponseEntity<String> getApiEchoboxProperties(@RequestParam(value = "authToken") String authToken,
			@RequestParam(value = "propertyId") String propertyId,
			@RequestParam(value = "propertyStateId") String propertyStateId,
			@RequestParam(value = "propertyTypeId") String propertyTypeId) {
		return echoboxAPIService.getApiEchoboxProperties(authToken, propertyId, propertyStateId, propertyTypeId);
	}

}
