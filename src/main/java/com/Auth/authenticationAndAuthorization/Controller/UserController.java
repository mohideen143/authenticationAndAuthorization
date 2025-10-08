package com.Auth.authenticationAndAuthorization.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Auth.authenticationAndAuthorization.Dto.UserDto;
import com.Auth.authenticationAndAuthorization.Entity.User;
import com.Auth.authenticationAndAuthorization.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/UserRegister")
	public ResponseEntity<UserDto> userRegister(@Valid @RequestBody UserDto userDto){
		userService.userRegister(userDto);
		return ResponseEntity.created(null).build();
		
	}
	
	@PostMapping("/Login")
	public ResponseEntity<?> userLogin(@Valid @RequestBody UserDto userDto){
		return userService.userLogin(userDto);
		
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return userService.getAllUsers();
		
	}
	
	

}
