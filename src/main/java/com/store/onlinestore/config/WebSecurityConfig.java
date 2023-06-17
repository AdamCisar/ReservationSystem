package com.store.onlinestore.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.security.AuthenticationFilter;

import io.jsonwebtoken.Jwt;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer{
	
	@Autowired
	private AuthenticationFilter authenticationFilter;
	@Autowired
	private UserRepository repository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	  public UserDetailsService userDetailsService() {
	    return username -> repository.findByEmail(username);
	  }
	
	 @Bean
	 public AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	  }
	 
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    String hierarchy = "ROLE_ADMIN > ROLE_USER";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}
	
	@Bean
	public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
	    expressionHandler.setRoleHierarchy(roleHierarchy());
	    return expressionHandler;
	}
	
	 @Bean
     public AuthenticationManager authenticationManager(
	                                 AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
     }
	 
	 @Bean
	 CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedMethods(List.of(
	                HttpMethod.GET.name(),
	                HttpMethod.PUT.name(),
	                HttpMethod.PATCH.name(),
	                HttpMethod.POST.name(),
	                HttpMethod.DELETE.name()
	        ));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
	        return source;
	    }
	
	@Bean
	protected DefaultSecurityFilterChain config(HttpSecurity http) throws Exception {
	    http.csrf()
		    .disable()
	        .authorizeHttpRequests()
		        .requestMatchers("/api/reservation/admin/**")
		        .hasRole("ADMIN")
		        .requestMatchers("api/reservation")
		        .hasAnyRole("USER", "ADMIN")
		        .requestMatchers("/api/register", "/api/login")
		        .permitAll()
		        .anyRequest()
		        .authenticated()
	        .and()
                .cors()
                .configurationSource(corsConfigurationSource())
		     .and()
	        	.sessionManagement()
	        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	         .and()
			      .authenticationProvider(authenticationProvider())
			      .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		    
		 
	    return http.build();
	}
}
