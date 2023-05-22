package com.customerRegistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerRegistration.dto.CustomerProfileDTO;
import com.customerRegistration.service.CustomerProfileServiceImpl;

@RestController
public class CustomerProfileController {

	@Autowired
	CustomerProfileServiceImpl customerProfileServiceImpl;

	@PostMapping("/createProfile")
	public String createCustomerProfile(@RequestBody CustomerProfileDTO customerProfileDTO) {
		String response = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
		return response;
	}

}
