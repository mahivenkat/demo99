package com.example.demo99;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Demo99ApplicationTests {
	@Autowired
	private MockMvc mockMvc;


	@Test
	public void getAllEmployees() throws Exception {
		mockMvc.perform(get("http://localhost:8080/api/v1/employee/employees")).andDo(print()).
				andExpect(status().isOk()).
				andExpect(jsonPath("$.*", hasSize(3)));
	}

}
