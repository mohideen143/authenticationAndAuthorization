package com.Auth.authenticationAndAuthorization.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Auth.authenticationAndAuthorization.Dto.UserDto;
import com.Auth.authenticationAndAuthorization.Service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/UserRegister")
	public ResponseEntity<UserDto> userRegister(@RequestBody UserDto userDto){
		userService.userRegister(userDto);
		return ResponseEntity.created(null).build();
		
	}
	
	@PostMapping("/Login")
	public ResponseEntity<?> userLogin(@RequestBody UserDto userDto){
		return userService.userLogin(userDto);
		
	}
	
	

}
