package com.tatha.springbootjwt.controller;

import java.util.ArrayList;
import java.util.List;

//import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import com.tatha.springbootjwt.model.LoginRequest;
import com.tatha.springbootjwt.model.LoginResponse;
import com.tatha.springbootjwt.service.MyUserDetailsService;
import com.tatha.springbootjwt.utils.JWTutility;



//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
@RestController
public class HomeController {
	
	@Autowired
    private JWTutility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping("/")                    //everyone
	public String homeMessage() {
		// return "<h1>home</h1>";  
        return "home"; 
	}
	
	@RequestMapping("/user")                //admin & user   
	public String userMessage() {
		//return "<h1>user</h1>"; 
        return "accesible demo  data for user";  
	}
	
	
	@RequestMapping("/admin")              //only admin   
	public String adminMessage() {
		//return "<h1>admin</h1>";  
        return "accesible demo  data only for admin"; 
	}
	
	
	
	
	//used for login with username & password for getting authenticated. It gives jwt after password natches
	@PostMapping("/login")   
    public LoginResponse authenticate(@RequestBody LoginRequest request) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails= userDetailsService.loadUserByUsername(request.getUsername());

        final String token =jwtUtility.generateToken(userDetails);

        //return  new LoginResponse(token);

        return  new LoginResponse(token, request.getUsername());
    }

}
