package com.sample.restapi.service.impl;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.restapi.service.RestClientService;

@SuppressWarnings("unchecked")
@Service
public class RestClientServiceImpl implements RestClientService {

	private static final String REST_URI = "https://httpbin.org";

	private static Client client = ClientBuilder.newClient();

	/*
	 * GET requests
	 */
	@Override
	public ResponseEntity<String> getRequest() {
		Response response = client.target(REST_URI).path("get").request(MediaType.APPLICATION_JSON).get();

		String responseJSONString = response.readEntity(String.class);
		HashMap<String, Object> responseMap;
		try {
			responseMap = new ObjectMapper().readValue(responseJSONString, HashMap.class);
		} catch (IOException e) {
			responseMap = new HashMap<>();
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body("url: " + responseMap.get("url") + "\n" + responseJSONString);
	}

	@Override
	public ResponseEntity<String> getRequestWithAuthorizationToken() {
		Response response = client.target(REST_URI).path("get").request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + "... encoded token ...").get();

		String responseJSONString = response.readEntity(String.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseJSONString);
	}

	@Override
	public ResponseEntity<String> getRequestWithBasicAuthentication() {
		Client clientWithBasicAuthentication = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
		clientWithBasicAuthentication.register(feature);
		Response response = clientWithBasicAuthentication.target(REST_URI).path("get").request(MediaType.APPLICATION_JSON)
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, "username")
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, "password").get();

		String responseJSONString = response.readEntity(String.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseJSONString);
	}

	/*
	 * POST requests
	 */
	@Override
	public ResponseEntity<String> postRequest() {
		Response response = client.target(REST_URI).path("post").request(MediaType.APPLICATION_JSON)
				.post(Entity.json(getExternalJson()));
		return ResponseEntity.status(HttpStatus.OK).body(response.readEntity(String.class));
	}

	@Override
	public ResponseEntity<String> postRequestWithAuthorizationToken(String authToken) {
		Response response = client.target(REST_URI).path("post").request(MediaType.APPLICATION_JSON)
				.header("X-AuthToken", authToken).post(Entity.json(getExternalJson()));
		return ResponseEntity.status(HttpStatus.OK).body(response.readEntity(String.class));
	}

	/**
	 * Get json String from external URL
	 * 
	 * @return String
	 */
	private String getExternalJson() {
		Response externalJson = client.target(REST_URI).path("get").request(MediaType.APPLICATION_JSON).get();
		return externalJson.readEntity(String.class);
	}

}
