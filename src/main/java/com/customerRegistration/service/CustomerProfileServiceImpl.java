package com.customerRegistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.customerRegistration.dto.CustomerProfileDTO;
import com.customerRegistration.dto.ErrorResponse;
import com.customerRegistration.dto.SuccessResponse;
import com.customerRegistration.model.User;
import com.customerRegistration.repository.CustomerProfileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomerProfileServiceImpl {

	@Autowired
	CustomerProfileRepository customerProfileRepository;
	

	public String createCustomerProfile(CustomerProfileDTO customerProfileDTO) {

		String response = null;

		List<ErrorResponse> listErrorResponse = validateCustomerProfile(customerProfileDTO);
		if (!CollectionUtils.isEmpty(listErrorResponse)) {
			response = prepareErrorResponse(listErrorResponse);
			return response;
		}

		User customerProfile = transformIntoCustomerEntity(customerProfileDTO);
		customerProfileRepository.insert(customerProfile);

		return prepareSuccessResponse();

	}

	private User transformIntoCustomerEntity(CustomerProfileDTO customerProfileDTO) {

		User customerProfile = new User();
		customerProfile.setLoginId(customerProfileDTO.getLoginId());
		customerProfile.setFirstName(customerProfileDTO.getFirstName());
		customerProfile.setLastName(customerProfileDTO.getLastName());
		customerProfile.setEmail(customerProfileDTO.getEmail());
		customerProfile.setPassword(customerProfileDTO.getPassword());
		customerProfile.setConfirmPassword(customerProfileDTO.getConfirmPassword());
		customerProfile.setContactNo(customerProfileDTO.getContactNo());

		return customerProfile;
	}

	private String prepareSuccessResponse() {
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setMessage("Your registration has been successfully completed");
		ObjectMapper objectMapper = new ObjectMapper();
		String response = null;
		try {
			response = objectMapper.writeValueAsString(successResponse);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	private String prepareErrorResponse(List<ErrorResponse> listErrorResponse) {
		ObjectMapper objectMapper = new ObjectMapper();
		String response = null;
		try {
			response = objectMapper.writeValueAsString(listErrorResponse);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	private List<ErrorResponse> validateCustomerProfile(CustomerProfileDTO customerProfileDTO) {

		List<ErrorResponse> listErrorResponse = new ArrayList<>();

//		if (StringUtils.isEmpty(customerProfileDTO.getfirstName())) {
//			ErrorResponse errorResponse = new ErrorResponse();
//			errorResponse.setName("Name");
//			errorResponse.setDetail("Name is mandatory or missing");
//			listErrorResponse.add(errorResponse);
//		}
//		if (StringUtils.isEmpty(customerProfileDTO.getUsername())) {
//			ErrorResponse errorResponse = new ErrorResponse();
//			errorResponse.setName("Username");
//			errorResponse.setDetail("Username is mandatory or missing");
//			listErrorResponse.add(errorResponse);
//		}
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{6,10}$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(customerProfileDTO.getPassword());

		if (!m.matches()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setName("password");
			errorResponse.setDetail(
					"Password should contain atleast 6 characters, uppercase, lowercase, special characters and numbers");

			listErrorResponse.add(errorResponse);
		}

		String regex1 = "^(.+)@(.+).(.+)$";

		Pattern p1 = Pattern.compile(regex1);

		Matcher m1 = p1.matcher(customerProfileDTO.getEmail());

		if (!m1.matches()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setName("Email");
			errorResponse.setDetail("Enter a Vaild Email Address");

			listErrorResponse.add(errorResponse);
		}

		Pattern p3 = Pattern.compile("[7-9][0-9]{9}");

		Matcher m3 = p3.matcher(customerProfileDTO.getContactNo());

		if (!m3.matches()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setName("MobileNumber");
			errorResponse.setDetail("Mobile number should have 10 digits");

			listErrorResponse.add(errorResponse);
		}

		
		
		Optional<User> userOpt= customerProfileRepository.findByUsername(customerProfileDTO.getLoginId());
		if(userOpt.isPresent()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setName("UserName");
			errorResponse.setDetail("UserName is already present");
			
			listErrorResponse.add(errorResponse);
		}

		return listErrorResponse;
	}

}
