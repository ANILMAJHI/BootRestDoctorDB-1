package com.anil.boot;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.anil.boot.model.Doctor;
import com.anil.boot.service.DoctoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctoryService doctoryService;

	@InjectMocks
	private Doctor doctor;

	@Before
	public void setUp() {
		doctor.setId(12);
		doctor.setName("anil");
		doctor.setSalary(12000);
		doctor.setSpecialize("dentest");
	}

	@Test
	public void testSaveDoctor() throws Exception
	{
		when(doctoryService.saveDoctor(Mockito.any(Doctor.class))).thenReturn(doctor);
		 ObjectMapper objectMapper = new ObjectMapper();
		 String stringJson= objectMapper.writeValueAsString(doctor);
		 mockMvc.perform(post("/api/savedoctor").contentType(MediaType.APPLICATION_JSON).content(stringJson)).andExpect(status().isOk());
	}
}
