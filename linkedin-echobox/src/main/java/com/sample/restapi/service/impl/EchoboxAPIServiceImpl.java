package com.sample.restapi.service.impl;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.restapi.service.EchoboxAPIService;

@SuppressWarnings("unchecked")
@Service
public class EchoboxAPIServiceImpl implements EchoboxAPIService {

	private static final String ECHOBOX_URL = "https://api.echobox.com/v3";
	private static final String EBX_AUTH_TOKEN = "X-EBX-AuthToken";

	private static Client client = ClientBuilder.newClient();

	@Override
	public ResponseEntity<String> getApiEchoboxUsers(String authToken, String userStateId, String permissions) {
		Response response = client.target(ECHOBOX_URL).path("users").queryParam("userStateId", userStateId)
				.queryParam("permissions", permissions).request(MediaType.APPLICATION_JSON).header(EBX_AUTH_TOKEN, authToken)
				.get();

		String responseJSONString = response.readEntity(String.class);
		HashMap<String, Object> responseMap;
		try {
			responseMap = new ObjectMapper().readValue(responseJSONString, HashMap.class);
		} catch (IOException e) {
			responseMap = new HashMap<>();
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body("headers: " + responseMap.get("headers") + "\n" + responseJSONString);
	}

	@Override
	public ResponseEntity<String> getApiEchoboxProperties(String authToken, String propertyId, String propertyStateId,
			String propertyTypeId) {
		Response response = client.target(ECHOBOX_URL).path("properties/" + propertyId)
				.queryParam("propertyStateId", propertyStateId).queryParam("propertyTypeId", propertyTypeId)
				.request(MediaType.APPLICATION_JSON).header(EBX_AUTH_TOKEN, authToken).get();

		return ResponseEntity.status(HttpStatus.OK).body(response.readEntity(String.class));
	}

}
