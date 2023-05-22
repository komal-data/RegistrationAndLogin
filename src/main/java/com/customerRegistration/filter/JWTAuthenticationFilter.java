package com.customerRegistration.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.customerRegistration.service.CustomUserDetailsService;
import com.customerRegistration.util.JWTUtil;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get jwt
		// Bearer
		// validate
		String requestTokenHeader = request.getHeader("Authorization");
		String loginId = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				loginId = jwtUtil.getUsernameFromToken(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}

			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginId);

			// security
			if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Token is not validated !!");
			}
				
		}
		
		filterChain.doFilter(request, response);
	}

}