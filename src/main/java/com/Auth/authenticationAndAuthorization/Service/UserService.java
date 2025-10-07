package com.Auth.authenticationAndAuthorization.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Auth.authenticationAndAuthorization.Dto.UserDto;
import com.Auth.authenticationAndAuthorization.Entity.User;
import com.Auth.authenticationAndAuthorization.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void userRegister(UserDto userDto) {
		User user = new User();
		
		user.setName(userDto.getName());
		user.setPhone(userDto.getPhone());
		user.setEmail(userDto.getEmail());
		user.setCreateDate(LocalDateTime.now());
		user.setPassword(userDto.getPassword());
		
		userRepository.save(user);
		
	}

	public ResponseEntity<?> userLogin(UserDto userDto) {
		
		User user = userRepository.findByEmailOrPhone(userDto.getEmail(), userDto.getPhone()).orElseThrow(() -> new RuntimeException("User Not Found"));
		
		if(userDto.getPassword().equals(user.getPassword())) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("user login successfully");	
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Credentials");
		}
			
	}

}
