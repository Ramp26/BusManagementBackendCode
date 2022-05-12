package com.collabera.bushub.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.collabera.bushub.dto.Bus;
import com.collabera.bushub.dto.AdminOwner;
import com.collabera.bushub.model.AdminOwnerModel;
import com.collabera.bushub.service.AdminOwnerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdminOwnerControllerTest {

	@MockBean
	private AdminOwnerService busOwnerService;
	


	@Autowired
	private WebApplicationContext applicationContext;

	private MockMvc mvc;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}

	@Test
	void testRegisterOwner() throws JsonProcessingException, UnsupportedEncodingException, Exception {

		AdminOwner busOwner = new AdminOwner();

	
		busOwner.setName("srujan");
		busOwner.setContact(988083190);
		busOwner.setAddress("belawadi");
		busOwner.setUserName("rock");
		busOwner.setPassword("Ram@123");
		busOwner.setRole("owner");

		AdminOwner busOwner2 = busOwnerService.regData(busOwner);
		when(busOwner2).thenReturn(busOwner2);
	

		String content=mvc.perform(put("/reg").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(busOwner2)).accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		ResponseEntity entity=mapper.readValue(content,ResponseEntity.class);
	
		assertEquals("successfully register", entity.getStatusCodeValue());

	}

	@Test
	void testGetOwner() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateOwner() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBus() {
		fail("Not yet implemented");
	}

	@Test
	void testAddBus() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		AdminOwnerModel bus=new AdminOwnerModel();
		
		bus.setBusId(1000);
		bus.setBusName("ksrtc");
		bus.setBusFacility("ac");
		bus.setBusFeature("sleeper");
		bus.setCharges(100.0);
		bus.setDistance(200);
		bus.setFromPlace("belawadi");
		bus.setToPlace("belgaum");
		Bus data=busOwnerService.editBusData(bus, 1000);
		when(data).thenReturn(data);
		
		String content=mvc.perform(put("/editbus/100").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(data)).accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
ResponseEntity response=mapper.readValue(content,ResponseEntity.class);
		
		System.out.println("result" + content);
		assertEquals("Updated successfully", response.getStatusCodeValue());
//		assertEquals("Updated successfully", response.getStatusCodeValue());
		
	}

	@Test
	void testUpdateData() {
		fail("Not yet implemented");
	}

	@Test
	void testGetbusDetails() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBusDetails() {
		fail("Not yet implemented");
	}

}
