package com.Auth.authenticationAndAuthorization.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Auth.authenticationAndAuthorization.Dto.UserDto;
import com.Auth.authenticationAndAuthorization.Entity.User;
import com.Auth.authenticationAndAuthorization.Repository.UserRepository;
import com.Auth.authenticationAndAuthorization.Security.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	public void userRegister(UserDto userDto) {
		User user = new User();
		
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		
		user.setName(userDto.getName());
		user.setPhone(userDto.getPhone());
		user.setEmail(userDto.getEmail());
		user.setCreateDate(LocalDateTime.now());
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		
	}

	public ResponseEntity<?> userLogin(UserDto userDto) {
		
		User user = userRepository.findByEmailOrPhone(userDto.getEmail(), userDto.getPhone())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		
		if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

			
			String token = jwtUtil.generateToken(user);
			System.out.println("token" + token);
			return ResponseEntity.ok(Map.of("token", "Bearer "+ token));
	}
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
			

	public ResponseEntity<List<UserDto>> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = new ArrayList<>(); 
		
		users.forEach(user -> {
			
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setPhone(user.getPhone());
			userDto.setEmail(user.getEmail());
			userDto.setCreateDate(user.getCreateDate());
			
			userDtos.add(userDto);
			
		});
		
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(userDtos);
	}

}
