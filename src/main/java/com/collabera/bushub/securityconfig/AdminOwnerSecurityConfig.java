package com.collabera.bushub.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.collabera.bushub.jwtFilter.JwtFilter;
import com.collabera.bushub.service.AdminOwnerServiceImpl;

@EnableWebSecurity
public class AdminOwnerSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AdminOwnerServiceImpl busOwnerService;
	
	@Autowired
	private JwtFilter filter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(busOwnerService);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.cors();
		http.authorizeHttpRequests().antMatchers("/reg").permitAll();
		http.authorizeHttpRequests().antMatchers("/login").permitAll();
		http.authorizeHttpRequests().antMatchers("/search").permitAll();
		http.authorizeHttpRequests().antMatchers("/get/{id}").permitAll();
		http.authorizeHttpRequests().antMatchers("/edit/{id}").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/remove/{id}").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/getbus/{busId}").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/addbus").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/editbus/{busId}").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/removebus/{busId}").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/getallbuses/{userName}").hasRole("owner");
		http.authorizeHttpRequests().antMatchers("/owners").permitAll();
		http.authorizeHttpRequests().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}

	
}
