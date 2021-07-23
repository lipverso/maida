package com.maida.demo.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maida.demo.model.User;
import com.maida.demo.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional <User> userOptional = userRepository.findByEmail(email);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), getPermissoes(user));
	}

	private java.util.Collection<? extends GrantedAuthority> getPermissoes(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		return authorities;
	}

}
