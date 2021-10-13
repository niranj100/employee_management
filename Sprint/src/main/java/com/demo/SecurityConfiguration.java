package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.demo.entities.Role;

@Configuration
@EnableWebSecurity
// implementation allows customization by overriding methods
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService; //Core interface which loads user-specific data. 



	@Override
	//Allows foreasily building in memory authentication, LDAP authentication, JDBC based authentication
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(userDetailsService)
									.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
		// DELETE and POST can be performed by ADMIN only
		//Allows restricting access based upon the HttpServletRequest using RequestMatcher implementations 
		.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole(Role.ADMIN)
		.antMatchers(HttpMethod.POST).hasRole(Role.ADMIN)
		.antMatchers("/employee/**","/department/**","/compliance/**").hasAnyRole(Role.ADMIN, Role.USER)
		.and()
		.authorizeRequests().anyRequest().permitAll().and()
        .exceptionHandling().accessDeniedPage("/403").and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic();
	}
	
	@Bean 
	//Service interface for encoding passwords.
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
