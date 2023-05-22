package com.customerRegistration.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_profile")
public class User {
	@Id
	public String loginId;
	@NonNull
	public String firstName;
	@NonNull
	public String lastName;
	@NonNull
	public String password;
	@NonNull
	public String confirmPassword;
	@NonNull
	public String email;
	@NonNull
	public String contactNo;
}