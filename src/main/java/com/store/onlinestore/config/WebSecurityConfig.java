package com.store.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public RoleHierarchy roleHierarchy() {
//	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//	    String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
//	    roleHierarchy.setHierarchy(hierarchy);
//	    return roleHierarchy;
//	}
//	
//	@Bean
//	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
//	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//	    expressionHandler.setRoleHierarchy(roleHierarchy());
//	    return expressionHandler;
//	}
	
	@Bean
	protected DefaultSecurityFilterChain config(HttpSecurity http) throws Exception {
	    http.csrf()
		    .disable()
	        .authorizeHttpRequests()
		        .requestMatchers("/admin")
		        .hasRole("USER")
		        .requestMatchers("/register")
		        .anonymous()
		        .requestMatchers("/all")
		        .permitAll()
		        .anyRequest()
		        .authenticated();
		 
	    return http.build();
	}
}
