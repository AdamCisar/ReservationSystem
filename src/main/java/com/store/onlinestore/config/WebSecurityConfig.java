package com.store.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    String hierarchy = "ROLE_ADMIN > ROLE_USER";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}
	
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
	    expressionHandler.setRoleHierarchy(roleHierarchy());
	    return expressionHandler;
	}
	
	@Bean
	protected DefaultSecurityFilterChain config(HttpSecurity http) throws Exception {
	    http.csrf()
		    .disable()
	        .authorizeHttpRequests()
		        .requestMatchers("/api/admin")
		        .hasRole("ADMIN")
		        .requestMatchers("/api/user")
		        .hasRole("USER")
		        .requestMatchers("/api/register", "api/reservation", "api/reservation/addUser")
		        .anonymous()
		        .requestMatchers("/all")
		        .permitAll()
		        .anyRequest()
		        .authenticated()
	    	.and()
	    		.formLogin();
		 
	    return http.build();
	}
}
