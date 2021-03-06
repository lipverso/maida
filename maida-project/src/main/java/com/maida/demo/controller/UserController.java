package com.maida.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maida.demo.model.User;
import com.maida.demo.repository.UserRepository;
import com.maida.demo.service.UserRegistrationService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRegistrationService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public ResponseEntity<User> registrate (@RequestBody User user){
		var newUser = userService.registrate(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@GetMapping("/{id}")
	public User listar(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow();
	}
	
	@GetMapping
	public List<User> listarTodos() {
		return userRepository.findAll();
	}
}
