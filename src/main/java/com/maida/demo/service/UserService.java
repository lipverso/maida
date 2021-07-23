package com.maida.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.maida.demo.entity.User;
import com.maida.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public ResponseEntity<User> validateRegister(User user) {
		var savedUser = userRepository.findById(user.getId());

		if (savedUser.isEmpty()) {
			userRepository.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
