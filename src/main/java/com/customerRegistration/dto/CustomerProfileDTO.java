package com.customerRegistration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileDTO{
    private String loginId;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String confirmPassword;
    public String contactNo;
	
}
