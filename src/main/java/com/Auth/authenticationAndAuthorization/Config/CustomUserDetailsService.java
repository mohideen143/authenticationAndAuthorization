package com.Auth.authenticationAndAuthorization.Config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Auth.authenticationAndAuthorization.Entity.User;
import com.Auth.authenticationAndAuthorization.Repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
		User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>() 
        );
		
	}

}
