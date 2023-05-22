package com.customerRegistration.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import com.customerRegistration.model.JWTResponse;
import com.customerRegistration.model.User;
import com.customerRegistration.service.CustomUserDetailsService;
import com.customerRegistration.util.JWTUtil;

@SpringBootTest(classes = JWTControllerTest.class)
class JWTControllerTest {

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	//@Autowired
	//MockMvc mockMvc;

	
	@MockBean
	private JWTUtil jwtTokenUtil;

	@MockBean
	private CustomUserDetailsService userDetailsService;
	
	@InjectMocks
	private JWTController controller;
	
	@MockBean
	private AuthenticationManager authenticationManager;
	@Test
	void testGenerateToken() throws Exception {
		//JWTRequest req = new JWTRequest("admin","admin");
		User user = new User(1,"admin", "admin");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.generateToken(details)).thenReturn("token");
		ResponseEntity<?> entity = controller.generateToken(user);
		assertThat(Integer.valueOf(entity.getStatusCodeValue()).equals(Integer.valueOf(200))).isTrue();

	}
	@Test
	void testNegativeGenerateToken() throws Exception {
		//JWTRequest req = new JWTRequest("admin1","admin");
		User user = new User(1,"admin1", "admin");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("user")).thenReturn(details);
		when(jwtTokenUtil.generateToken(details)).thenReturn("token");
		ResponseEntity<?> entity = controller.generateToken(user);
		assertThat(Integer.valueOf(entity.getStatusCodeValue()).equals(Integer.valueOf(200))).isTrue();

	}
	
	
	@Test
	public void testValidity() throws Exception {
		JWTResponse response=new JWTResponse("Bearer token");
		when(jwtTokenUtil.getUsernameFromToken("Bearer token")).thenReturn(null);
		assertEquals(controller.validateToken(response),"false");
	}
	
	@Test
	public void testAuthorizationInvalid() throws Exception {
		User user = new User(1,"admin", "admin");
		JWTResponse response=new JWTResponse("token");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		assertEquals(controller.validateToken(response),"false");

	}
	
	@Test
	public void testAuthorizationNullUser() throws Exception {

		User user = new User(1,"admin", "admin");
		JWTResponse response=new JWTResponse("WrongToken");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		
		assertEquals(controller.validateToken(response),"false");

	}
}
