package com.customerRegistration.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.customerRegistration.util.JWTUtil;

import com.customerRegistration.model.User;
import com.customerRegistration.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationControllerEndPointsTest {

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomUserDetailsService userDetailsService;

	@MockBean
	private JWTUtil jwtTokenUtil;

	@MockBean
	private AuthenticationManager authenticationManager;

	@Test
	void testBadRequestGenerateToken() throws Exception {
		this.mockMvc.perform(post("/login")).andExpect(status().isBadRequest());
	}

	@Test
	void testAuthorizedGenerateToken() throws Exception {

		User user = new User(1, "Abhi123", "Abhi@123");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
		when(jwtTokenUtil.generateToken(details)).thenReturn("token");
		when(userDetailsService.loadUserByUsername("Abhi123")).thenReturn(details);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(new User(1,"Abhi123", "pass"));
		this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isOk());
	}

	@Test
	void testBadRequest() throws Exception {
		this.mockMvc.perform(post("/login")).andExpect(status().isBadRequest());
	}


	@Test
	void textExistingUserAuthenticate() throws Exception {
		User user = new User(1, "Abhi123", "Abhi@123");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				"Abhi123", "password");
		when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("Abhi123", "password")))
				.thenReturn(usernamePasswordAuthenticationToken);
		when(userDetailsService.loadUserByUsername("Abhi123")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("Abhi123");
		when(jwtTokenUtil.generateToken(details)).thenReturn("token");
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new User(1,"Abhi123", "Abhi@123")))).andExpect(status().isOk());

	}

	
}
