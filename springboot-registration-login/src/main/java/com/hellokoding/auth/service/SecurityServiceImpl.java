package com.hellokoding.auth.service;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

 

/*
 * @Autowired enables you to inject the object dependency implicitly. 
 * It uses internal setters or constructors injections.
 * org.slf4j.Logger is the main user entry point with the method debug
 * returns msg at DEBUG level
 */

 

@Service
public class SecurityServiceImpl implements SecurityService {
    
// AuthenticationManager assumes the job of establishing user's identity.
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

 

    @Override
    public String findLoggedInUsername() {
        //Security Context Holder store details of the present security context app,
        // which includes details of the principal currently using of the app.
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        
        if (userDetails instanceof UserDetails) {
            //we return the information about the current user
            return (((UserDetails) userDetails).getUsername());
        }
        return null;
    }

 

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // This token is equivalent as we use with JWT service before
        UsernamePasswordAuthenticationToken usernamePasswordAutheticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        
        authManager.authenticate(usernamePasswordAutheticationToken);
        
        if (usernamePasswordAutheticationToken.isAuthenticated()) {
            //If everything is going ok and the user has been correctly Authenticate
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAutheticationToken);
            logger.debug(String.format("Auto login %s successfully", username));
        }
    }

 

}
