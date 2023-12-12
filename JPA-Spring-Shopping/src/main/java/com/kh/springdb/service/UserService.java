package com.kh.springdb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.Users;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;
/*
@RequiredArgsConstructor
@Service */
public class UserService {
	/*
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Users create(String userName, String email, String password) {
		Users user = new Users();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	} */
}
