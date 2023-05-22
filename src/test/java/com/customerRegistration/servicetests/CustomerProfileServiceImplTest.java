package com.customerRegistration.servicetests;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.customerRegistration.dto.CustomerProfileDTO;
import com.customerRegistration.repository.CustomerProfileRepository;
import com.customerRegistration.service.CustomerProfileServiceImpl;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class CustomerProfileServiceImplTest {
	
	 @InjectMocks
	    CustomerProfileServiceImpl customerProfileServiceImpl = new CustomerProfileServiceImpl();



	   @Mock
	    CustomerProfileRepository customerProfileRepository;
	   
	   @Test
	    @DisplayName("Checking the method with Empty Response")
	    public void testCreateCustomerProfile_EmptyRequestBody_ErrorResponse() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();



	       customerProfileDTO.setPassword("Ajita123");
	        customerProfileDTO.setEmail("ajitagmail");
	        customerProfileDTO.setAddress("B 203, Elciars Apartment, Floor No., 13, Sector 49, Gurgaon, 121011");
	        customerProfileDTO.setPan("CHGIR456O");
	        customerProfileDTO.setContactNo("90909090");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "[{\"name\":\"Name\",\"detail\":\"Name is mandatory or missing\"},{\"name\":\"Username\",\"detail\":\"Username is mandatory or missing\"},{\"name\":\"password\",\"detail\":\"Password should contain atleast 6 characters, uppercase, lowercase, special characters and numbers\"},{\"name\":\"address\",\"detail\":\"Address should be less than 50 characters\"},{\"name\":\"state\",\"detail\":\"State field is Mandatory\"},{\"name\":\"country\",\"detail\":\"Country field is Mandatory\"},{\"name\":\"Email\",\"detail\":\"Enter a Vaild Email Address\"},{\"name\":\"Pan\",\"detail\":\"Enter a Vaild PAN Number\"},{\"name\":\"AccountType\",\"detail\":\"Account Type should be Salary or Savings\"},{\"name\":\"MobileNumber\",\"detail\":\"Mobile number should have 10 digits\"},{\"name\":\"DOB\",\"detail\":\"DOB is incorrect or missing\"}]";



	       assertEquals(actual, expected);
	    }



	   @Test
	    @DisplayName("Checking the method with Invalid Address")
	    public void testCreateCustomerProfile_EmptyRequestBody_AddressLimitExceed() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();



	       customerProfileDTO.setAddress("B 203, Elciars Apartment, Floor No., 13, Sector 49, Gurgaon, 121011");



	       customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setPassword("Ajita@123");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setEmailAddress("ajita@gmail.com");
	        customerProfileDTO.setPan("CHGIR4560O");
	        customerProfileDTO.setMobileNo("9430615330");
	        customerProfileDTO.setAccountType("SAVING");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "[{\"name\":\"address\",\"detail\":\"Address should be less than 50 characters\"}]";



	       assertEquals(actual, expected);
	    }
	   
	   @Test
	    @DisplayName("Checking the method with Invalid Password")
	    public void testCreateCustomerProfile_EmptyRequestBody_InvalidPassword() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();



	       customerProfileDTO.setPassword("Ajita123");



	       customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setAddress("123 Gurgaon");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setEmailAddress("ajita@gmail.com");
	        customerProfileDTO.setPan("CHGIR4560O");
	        customerProfileDTO.setMobileNo("9430615330");
	        customerProfileDTO.setAccountType("SAVING");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "[{\"name\":\"password\",\"detail\":\"Password should contain atleast 6 characters, uppercase, lowercase, special characters and numbers\"}]";



	       assertEquals(actual, expected);
	    }



	   @Test
	    @DisplayName("Checking the method with Invalid Email")
	    public void testCreateCustomerProfile_EmptyRequestBody_InvalidEmail() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();



	       customerProfileDTO.setEmailAddress("ajitagmail");



	       customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setPassword("Ajita@123");
	        customerProfileDTO.setAddress("123 Gurgaon");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setPan("CHGIR4560O");
	        customerProfileDTO.setMobileNo("9430615330");
	        customerProfileDTO.setAccountType("SAVING");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "[{\"name\":\"Email\",\"detail\":\"Enter a Vaild Email Address\"}]";



	       assertEquals(actual, expected);
	    }



	   @Test
	    @DisplayName("Checking the method with Invalid Pan")
	    public void testCreateCustomerProfile_EmptyRequestBody_InvalidPan() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();



	       customerProfileDTO.setPan("CHGIR456O");



	       customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setPassword("Ajita@123");
	        customerProfileDTO.setAddress("123 Gurgaon");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setEmailAddress("ajita@gmail.com");
	        customerProfileDTO.setMobileNo("9430615330");
	        customerProfileDTO.setAccountType("SAVING");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "[{\"name\":\"Pan\",\"detail\":\"Enter a Vaild PAN Number\"}]";



	       assertEquals(actual, expected);
	    }



	   @Test
	    @DisplayName("Checking the method with Invalid Mobile No.")
	    public void testCreateCustomerProfile_EmptyRequestBody_InvalidMobileNo() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();



	       customerProfileDTO.setMobileNo("90909090");



	       customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setPassword("Ajita@123");
	        customerProfileDTO.setAddress("123 Gurgaon");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setEmailAddress("ajita@gmail.com");
	        customerProfileDTO.setPan("CHGIR4560O");
	        customerProfileDTO.setAccountType("SAVING");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "[{\"name\":\"MobileNumber\",\"detail\":\"Mobile number should have 10 digits\"}]";



	       assertEquals(actual, expected);
	    }



	   @Test
	    @DisplayName("Checking the method with Valid Response")
	    public void testCreateCustomerProfile_ValidRequestBody_SuccessResponse() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();
	        customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setPassword("Ajita@123");
	        customerProfileDTO.setAddress("123 Gurgaon");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setEmailAddress("ajita@gmail.com");
	        customerProfileDTO.setPan("CHGIR4560O");
	        customerProfileDTO.setMobileNo("9430615330");
	        customerProfileDTO.setAccountType("SALARY");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "{\"message\":\"Your registration has been successfully completed\"}";



	       assertEquals(actual, expected);
	    }



	   @Test
	    @DisplayName("Checking the method with Saving Account Type")
	    public void testCreateCustomerProfile_SavingAccountType() {



	       CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();
	        customerProfileDTO.setName("Ajita");
	        customerProfileDTO.setUsername("ajita");
	        customerProfileDTO.setPassword("Ajita@123");
	        customerProfileDTO.setAddress("123 Gurgaon");
	        customerProfileDTO.setState("Haryana");
	        customerProfileDTO.setCountry("India");
	        customerProfileDTO.setEmailAddress("ajita@gmail.com");
	        customerProfileDTO.setPan("CHGIR4560O");
	        customerProfileDTO.setMobileNo("9430615330");
	        customerProfileDTO.setAccountType("SAVING");
	        customerProfileDTO.setDateOfBirth("29-03-1999");



	       String actual = customerProfileServiceImpl.createCustomerProfile(customerProfileDTO);
	        String expected = "{\"message\":\"Your registration has been successfully completed\"}";



	       assertEquals(actual, expected);
	    }
}
