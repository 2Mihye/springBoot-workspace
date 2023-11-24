package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.springdb.model.User;
import com.kh.springdb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	// 전체 아이디를 가져오기 위해 GetMapping을 사용
	@GetMapping("/users-info")
	public String getAllUsers(Model model){ // Model을 넣어야지 값을 추가할 수 있으므로 model 넣기
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users-info";
	}
	
	@GetMapping("/user-info/{id}")
	// 하나의 아이디 가져오기
	public String getUserById(@PathVariable int id, Model model) { // PathVariable이 int id가 getUserById를 바라보게 만듬
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user-info"; // return "아이디 값을 전달받을 템플릿 html 이름";
	}
		
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User()); // model.저장
		return "register"; // return "템플릿명"
	}
	
	@PostMapping("/api/user/register")
	public String registerMember(@ModelAttribute("user") @Validated User user, BindingResult result) {
		userService.registerUser(user);
		return "redirect:/register?success"; // 유저가 회원가입을 성공할 경우 이동할 경로
	}
	
}

/*
 @PathVariable : 경로에 대한 변수를 메서드의 매개변수로 받을 때 사용
 		  사용법 : @PathVariable int id
 		  
 @ModelAttribute("값") : Thymeleaf View에서 설정한 값의 이름을 사용하여 Model 속성에 엑서스할 수 있음
 						*엑서스 : 컴퓨터 데이터 또는 리소스를 어떤 방식으로든 사용할 수 있도록 권한을 주거나 권한이 담겨진 것을 의미 
 		  사용법 : @ModelAttribute("user") : user 이라는 이름으로 Model에 User 객체를 추가한 것
 
 @Validated : 데이터 유효성 검사를 실시하도록 행하는 것
 		  사용법 : @Validated(user) : User 객체에 대한 데이터 유효성 검사를 실시하겠다라는 것
 
 BindingResult : @Validated에서 실시한 유효성 결과 검사를 저장하는 객체로 유효성 검사에서 발생한 오류에 대한 정보가 담기는 공간
 */
