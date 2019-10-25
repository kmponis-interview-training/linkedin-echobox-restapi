package com.sample.restapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.restapi.properties.ResponseProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

	@Autowired
	private ResponseProperties responseProperties;

	@Autowired
	private MockMvc mockMvc;

	private static final String indexUrl = "/";

	/*
	 * Test URL '/'
	 */
	@Test
	public void whenIndex_thenAssertResponseMessage() throws Exception {
		this.mockMvc.perform(get(indexUrl)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(responseProperties.index)));
	}

}
