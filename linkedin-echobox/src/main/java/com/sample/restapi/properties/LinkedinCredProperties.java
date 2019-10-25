package com.sample.restapi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:linkedin-cred-kostas.properties" })
public class LinkedinCredProperties {

	@Value("${client.id}")
	public String clientId;

	@Value("${client.secret}")
	public String clientSecret;

	@Value("${redirect.url}")
	public String redirectUrl;

	@Value("${code}")
	public String code;

}
