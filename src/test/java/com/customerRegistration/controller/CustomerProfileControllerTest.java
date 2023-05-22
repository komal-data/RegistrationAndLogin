package com.customerRegistration.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.customerRegistration.controller.CustomerProfileController;
import com.customerRegistration.dto.CustomerProfileDTO;
import com.customerRegistration.service.CustomerProfileServiceImpl;

@SpringBootTest
public class CustomerProfileControllerTest {

	@InjectMocks
	CustomerProfileController customerProfileController;

	@Mock
	CustomerProfileServiceImpl customerProfileServiveImpl;

	@Test
	@DisplayName("Checking if the customer profile controller is loading or not")
	void testCustomerContoller_NotNull() {

		assertThat(customerProfileController).isNotNull();

	}

	@Test
	@DisplayName("Testing Customer profile with success response")
	public void testCreateCustomerProfile_InvalidRequest_SuccessResponse() {
		
		

		Mockito.when(customerProfileServiveImpl.createCustomerProfile(Mockito.any())).thenReturn("MockObject");
		String response = customerProfileController.createCustomerProfile(new CustomerProfileDTO());
		String expect = "MockObject";
		assertEquals(response, expect);
	}
	
	

}
