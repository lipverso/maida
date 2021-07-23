package com.maida.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.maida.demo.exception.UserAlreadyRegisteredException;
import com.maida.demo.model.User;
import com.maida.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User validate(User user) {
		var savedUser = userRepository.findByEmail(user.getEmail());
		
		if (savedUser != null) {
			throw new UserAlreadyRegisteredException("Este usuário já está registrado");
		}
		
		return userRepository.save(user);
		
	}

}
