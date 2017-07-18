package com.epam.theatre.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md4PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.epam.theatre.service.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(authenticationService);
		provider.setPasswordEncoder(new Md4PasswordEncoder());
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**").permitAll();

		http.//
				formLogin().//
				loginPage("/login").//
				permitAll();

		http.//
				authorizeRequests().//
				antMatchers("/ticket/event-schedule/**").//
				access("hasRole('BOOKING_MANAGER')");

		http.//
				authorizeRequests().//
				antMatchers("/**").//
				access("hasRole('RESGISTERED_USER')");

		http.//
				exceptionHandling().//
				accessDeniedPage("/error");

		http.//
				rememberMe().//
				key("rem-me-key").//
				rememberMeParameter("remember-me-param").//
				rememberMeCookieName("my-remember-me").//
				userDetailsService(authenticationService).//
				tokenValiditySeconds(86400);

		http.//
				logout().//
				permitAll().//
				logoutUrl("/logout").//
				logoutSuccessUrl("/login").//
				invalidateHttpSession(true);
	}

}