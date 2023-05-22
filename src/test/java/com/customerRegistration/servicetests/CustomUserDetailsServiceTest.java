package com.customerRegistration.servicetests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.customerRegistration.model.User;
import com.customerRegistration.repository.LoginRepository;
import com.customerRegistration.service.CustomUserDetailsService;
@SpringBootTest
public class CustomUserDetailsServiceTest {
	
			
		@Mock
		private LoginRepository loginrepo;

		@InjectMocks
		private CustomUserDetailsService service;
		
		
		@BeforeEach
		void setUp() throws Exception {
		}

		@Test
		void loadUserByUserNameShouldThrowExceptionTest() {
			when(loginrepo.findByUsername("wrongUserName")).thenReturn(null);
			assertThatThrownBy(() -> service.loadUserByUsername("wrongUserName")) 
	        .isInstanceOf(UsernameNotFoundException.class)
	        .hasMessage("User not found !!");
			verify(loginrepo, Mockito.times(1)).findByUsername("wrongUserName");
		}
		
		@Test
		void loadUserByUserNameShouldUserNameTest() {
			when(loginrepo.findByUsername("Abhi123")).thenReturn(new User(1, "Abhilash", "Abhi123", "Abhi@123", "xyz", "pqr", "india", "shyam123@gnail.com", "ALWPG5809L", "08-01-2000", "SAVING", "7091735618"));
			assertThat(service.loadUserByUsername("Abhi123")).isNotNull();
			verify(loginrepo, Mockito.times(1)).findByUsername("Abhi123");
		}
		
		

	}
