package com.maida.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maida.demo.entity.User;
import com.maida.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	public ResponseEntity<User> register(@RequestBody User user){
			return userService.validateRegister(user);
	}
}
