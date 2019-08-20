package com.hellokoding.auth.service;

 

import java.util.HashSet;
import java.util.Set;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

 

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.model.Role;
import com.hellokoding.auth.repository.UserRepository;

 


// With this class we provide the app an authentication service

 

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepo;

 

    @Override
    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        //First we search if our user exists
        User user = userRepo.findByUsername(username);
        // In case it doesn't exist we have a null value, and it is treated as an exception
        if (user == null) throw new UsernameNotFoundException (username);
        
        //Otherwise, the user should have a role which provides some services
        // and privileges to the user        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}