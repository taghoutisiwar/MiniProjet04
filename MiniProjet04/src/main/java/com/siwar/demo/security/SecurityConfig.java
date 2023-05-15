package com.siwar.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
	PasswordEncoder passwordEncoder = passwordEncoder ();
	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	System.out.println("Password Encoded BCRYPT :******************** ");
	System.out.println(passwordEncoder.encode("123")); 
	}
	@Bean
	 public PasswordEncoder passwordEncoder () {
	 return new BCryptPasswordEncoder();
	 }

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests().antMatchers("/showCreate").hasAnyRole("ADMIN","AGENT");
			
			http.authorizeRequests().antMatchers("/saveVoiture").hasAnyRole("ADMIN","AGENT");
			
			http.authorizeRequests().antMatchers("/ListeVoiture")
			.hasAnyRole("ADMIN","AGENT","USER");
			
			http.authorizeRequests()
			.antMatchers("/supprimerVoiture","/modifierVoiture","/updateVoiture")
			.hasRole("ADMIN");
			
			http.authorizeRequests().antMatchers("/login").permitAll();
			
			//pour faire fonctionner Bootstrap
			http.authorizeRequests().antMatchers("/webjars/**").permitAll();
			
			//anyRequests() doit être la dernière (pas de antMatchers après anyRequests)
			http.authorizeRequests().anyRequest().authenticated();
		
			http.formLogin().loginPage("/login");	
			http.exceptionHandling().accessDeniedPage("/accessDenied");
	 }
	

}