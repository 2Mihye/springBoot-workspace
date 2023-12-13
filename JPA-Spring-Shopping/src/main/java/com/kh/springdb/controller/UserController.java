package com.kh.springdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.UserCheckForm;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user") // 공통으로 들어가는 url이 있다면 RequestMapping을 사용하여 user로 묶어주기
public class UserController {
	
	private final UserService userService;
	
	// 회원가입 창
	@GetMapping("/signup")
	public String signUp(UserCheckForm userCheckForm) {
		return "signup_form";
	}
	
	// 회원가입에 대한 PostMapping
	@PostMapping("/signup")
	public String signUp(@Valid UserCheckForm userCheckForm, BindingResult bindingResult) {
		if(!userCheckForm.getPassword1().equals(userCheckForm.getPassword2())) { // 만약 패스워드 2개가 일치하지 않는다면 일치하지 않습니타 메세지!
			bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "signup_form";
		}
		
		// HTML 폼에서 선택한 역할을 가져오기 위해 
		UserRole role = userCheckForm.getIsRole();
		userService.create(userCheckForm.getUserName(), userCheckForm.getEmail(), userCheckForm.getPassword1(), role);
		//userService.create(userCheckForm.getUserName(), userCheckForm.getEmail(), userCheckForm.getPassword1());
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}