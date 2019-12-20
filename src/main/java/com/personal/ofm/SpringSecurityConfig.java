/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.personal.ofm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class SpringSecurityConfig.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailservice;

	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css2/**", "/js2/**", "/vendor/**", "/img/**", "/scss/**", "/fonts2/**", "/images/**", "/recursos/**").permitAll()
				.antMatchers("/").hasAnyRole("ADMIN")
				.antMatchers("/detalle/vista").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll()
				.and()
	            .logout()
	            .logoutSuccessUrl("/logout")
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .permitAll().and()
	            .csrf().disable();
		
		/*Token para poder editar datos
		 * .and()
          .csrf().disable()*/
	}

	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailservice);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * Configure global.
	 *
	 * @param builder the builder
	 * @throws Exception the exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		// configuración global de los permisos
		builder.userDetailsService(userDetailservice)
		       .passwordEncoder(passwordEncoder)
		       .and()
			   .authenticationProvider(authenticationProvider());
	}

}