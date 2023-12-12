package com.kh.springdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.UserCheckForm;
import com.kh.springdb.service.UserService;

import lombok.RequiredArgsConstructor;
/*
@RequiredArgsConstructor
@Controller
@RequestMapping("/user") */
public class UserController {
	/*
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signUp(UserCheckForm userCheckForm) {
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid UserCheckForm usercheckform, BindingResult bindingResult) {
		if(!usercheckform.getPassword1().equals(usercheckform.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordCorrect", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "signup_form";
		}
		userService.create(usercheckform.getUserName(), usercheckform.getEmail(), usercheckform.getPassword1());
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}*/
}
