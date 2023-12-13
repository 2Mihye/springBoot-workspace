package com.kh.springdb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.UserRole;
import com.kh.springdb.model.Users;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Users create(String userName, String email, String password, UserRole role) { // 회원가입을 할 경우 계정을 생성하기 위해 Service를 만들어줌.
		// 기존 서비스에서 했던 회원가입과 다른 점은 비밀번호를 암호화 처리하여 저장해주는 것이 조금 다름.
		Users user = new Users();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		// 사용자 역할 설정
		user.setIsRole(role);
		
		userRepository.save(user);
		return user;
	}
}
