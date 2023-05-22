package com.customerRegistration.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.customerRegistration.repository.LoginRepository;
import com.customerRegistration.util.JWTUtil;
@SpringBootTest(classes = UserDetailsTest.class)
public class UserDetailsTest {

	@Mock
	LoginRepository repository;

	@InjectMocks
	JWTUtil jwtUtil;

	UserDetails userDetails;


	@Test
	public void testLoginRepositoryObjectNotNull() {
		assertNotNull(repository);
	}

	@Test
	public void testJWTUtilObjectNotNull() {
		assertNotNull(jwtUtil);
	}

	@Test
	public void testGenerateToken() {
		userDetails = new User("Abhi123", "Abhi@123", new ArrayList<>());
		String token = jwtUtil.generateToken(userDetails);
		System.out.println(token);
		assertNotNull(token);
	}

	@Test
	public void testValidateToken() {
		userDetails = new User("Abhi123", "Abhi@123", new ArrayList<>());
		String token = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(token, userDetails);
		assertTrue(validateToken);
	}

	@Test
	public void testValidateTokenWithCorrectUserDetails() {
		userDetails = new User("Abhi123", "Abhi@123", new ArrayList<>());
		String token = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(token, userDetails);
		assertTrue(validateToken);
	}

	@Test
	public void testValidateTokenWithIncorrectUsername() {
		userDetails = new User("Abhi123", "Abhi@123", new ArrayList<>());
		String token = jwtUtil.generateToken(userDetails);
		UserDetails actualUserDetails = new User("hjdc", "hhd", new ArrayList<>());
		Boolean validateToken = jwtUtil.validateToken(token, actualUserDetails);
		assertFalse(validateToken);
	}

	@Test
	public void extractUsernameTest() {
		userDetails = new User("Abhi123", "Abhi@123", new ArrayList<>());
		String token = jwtUtil.generateToken(userDetails);
		String username = jwtUtil.getUsernameFromToken(token);
		assertEquals(username, "Abhi123");
	}}
