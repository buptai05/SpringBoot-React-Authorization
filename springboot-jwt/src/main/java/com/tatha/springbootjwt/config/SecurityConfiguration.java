package com.tatha.springbootjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tatha.springbootjwt.filter.JWTRequestFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    UserDetailsService userDetailsService;
	
	
	@Autowired
    private JWTRequestFilter jwtReqFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        
        }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    
    @Override
    protected void configure(HttpSecurity httpsecurity) throws Exception {
//    	httpsecurity.authorizeRequests()
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .antMatchers("/user").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/").permitAll()
//                .and().formLogin();
    	
    	
    //	csrf().disable() is used for preventing some login glitches
    	
//    	httpsecurity.csrf().disable()
//		.authorizeRequests().antMatchers("/login").permitAll().
//		        antMatchers("/admin").hasAuthority("ADMIN").
//				anyRequest().authenticated().and().
//				exceptionHandling().and().sessionManagement()
//		       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                httpsecurity.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
                
        
    //	.cors().and() added to prevent cors error
    	httpsecurity.cors().and().csrf().disable()
         .authorizeRequests().antMatchers("/login").permitAll().
        		    antMatchers("/admin").hasAuthority("ADMIN").
        			anyRequest().authenticated().and().
        			exceptionHandling().and().sessionManagement()
        		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    httpsecurity.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);

    }
    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
