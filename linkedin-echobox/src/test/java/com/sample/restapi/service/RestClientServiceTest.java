package com.sample.restapi.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.restapi.service.impl.RestClientServiceImpl;

@SuppressWarnings("deprecation")
@RunWith(JUnitPlatform.class)
@SpringBootTest
public class RestClientServiceTest {

	private static final String REST_URI = "https://mock-url.com";

	@InjectMocks
	private RestClientService restClientService = new RestClientServiceImpl();

	@Mock
	private Client mockClient;
	@Mock
	private WebTarget mockWebTarget;
	@Mock
	private Builder mockBuilder;
	@Mock
	private Response mockResponse;
	@Mock
	private ObjectMapper mockObjectMapper;
	@Mock
	private BodyBuilder mockBodyBuilder;

	/*
	 * GET requests
	 */
	@Test
	public void testGetRequest() {
		String responseJSONString = "{ \"url\":\"John\" }";
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(responseJSONString);

		Mockito.when(mockClient.target(REST_URI)).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.path("get")).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.get()).thenReturn(mockResponse);
		Mockito.when(mockResponse.readEntity(String.class)).thenReturn(responseJSONString);

		try {
			Mockito.when(mockObjectMapper.readValue(responseJSONString, HashMap.class))
					.thenReturn(Mockito.mock(HashMap.class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mockito.when(mockBodyBuilder.body(Matchers.anyString())).thenReturn(responseEntity);

		assertTrue(restClientService.getRequest().getBody().contains("url: https://httpbin.org/get"));
	}

	@Test
	public void testGetRequestWithAuthorizationToken() {
		String responseJSONString = "{ \"url\":\"John\" }";
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(responseJSONString);

		Mockito.when(mockClient.target(REST_URI)).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.path("get")).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.header(Matchers.anyString(), Matchers.anyString())).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.get()).thenReturn(mockResponse);
		Mockito.when(mockResponse.readEntity(String.class)).thenReturn(responseJSONString);

		Mockito.when(mockBodyBuilder.body(Matchers.anyString())).thenReturn(responseEntity);

		assertTrue(restClientService.getRequestWithAuthorizationToken().getBody().contains("{"));
	}

	@Test
	public void testGetRequestWithBasicAuthentication() {
		String responseJSONString = "{ \"url\":\"John\" }";
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(responseJSONString);

		Mockito.when(mockClient.target(REST_URI)).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.path("get")).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.property(Matchers.anyString(), Matchers.anyString())).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.get()).thenReturn(mockResponse);
		Mockito.when(mockResponse.readEntity(String.class)).thenReturn(responseJSONString);

		try {
			Mockito.when(mockObjectMapper.readValue(responseJSONString, HashMap.class))
					.thenReturn(Mockito.mock(HashMap.class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mockito.when(mockBodyBuilder.body(Matchers.anyString())).thenReturn(responseEntity);

		assertTrue(restClientService.getRequestWithBasicAuthentication().getBody().contains("{"));
	}

	/*
	 * POST requests
	 */
	@Test
	public void testPostRequest() {
		String responseJSONString = "{ \"url\":\"John\" }";
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(responseJSONString);

		Mockito.when(mockClient.target(REST_URI)).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.path("post")).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.post(Matchers.any())).thenReturn(mockResponse);
		Mockito.when(mockResponse.readEntity(String.class)).thenReturn(responseJSONString);

		Mockito.when(mockBodyBuilder.body(Matchers.anyString())).thenReturn(responseEntity);

		assertTrue(restClientService.postRequest().getBody().contains("{"));
	}

	@Test
	public void testPostRequestWithAuthorizationToken() {
		String responseJSONString = "{ \"url\":\"John\" }";
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(responseJSONString);

		Mockito.when(mockClient.target(REST_URI)).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.path("post")).thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mockBuilder);
		Mockito.when(mockBuilder.post(Matchers.any())).thenReturn(mockResponse);
		Mockito.when(mockResponse.readEntity(String.class)).thenReturn(responseJSONString);

		Mockito.when(mockBodyBuilder.body(Matchers.anyString())).thenReturn(responseEntity);

		assertTrue(restClientService.postRequestWithAuthorizationToken("asd").getBody().contains("{"));
	}
}
