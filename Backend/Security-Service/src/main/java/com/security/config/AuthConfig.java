package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.service.CustomUserDetailService;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthConfig.
 */
@Configuration
@EnableWebSecurity
public class AuthConfig {

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Authentication manager.
	 *
	 * @param authenticationConfiguration the authentication configuration
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/**
	 * User details service.
	 *
	 * @return the user details service
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}

	/**
	 * Security filter chain.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/auth/addUser","/auth/addExpert","/auth/addCred", "/auth/generateToken", "/auth/validateToken/**","/user/register","/expert/register").permitAll()
						.requestMatchers("/expert/addissue/**").hasAnyRole("user","User")
						.requestMatchers("/user/**").hasAnyRole("user","User")
						.anyRequest().authenticated())
				.build();
	}

	/**
	 * Authentication provider.
	 *
	 * @return the authentication provider
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

}
