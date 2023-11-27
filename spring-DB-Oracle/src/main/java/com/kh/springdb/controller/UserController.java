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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@GetMapping("/registerSuccess")
	public String getRegisterSuccess() {
		return "registerSuccess";
	}
	
	@PostMapping("/api/user/register")
	public String registerMember(@ModelAttribute("user") @Validated User user, BindingResult result) {
		userService.registerUser(user);
		return "redirect:/registerSuccess"; // 유저가 회원가입을 성공할 경우 이동할 경로
	}
	
	@GetMapping("/user-update/{id}") // 사용자 정보를 가져와서 Model에 추가
	public String updateUserForm(@PathVariable int id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user-update";
	}

	
	@PostMapping("/api/user/update/{id}")
	public String updateUser(@PathVariable int id, @ModelAttribute("user") @Validated User user, BindingResult result) { // id에 해당하는 Update문을 사용하여 수정하기
		// 결과에 에러가 있는지 없는지 검증하는 if문 추가
		if(result.hasErrors()) {
			return "에러가 발견되었습니다.";
		}
		user.setMno(id); // 경로에서 받은 id를 이용하여 사용자 정보를 update한다.
		userService.updateUser(user);
		return "redirect:/user-info";
	}
	
	@RequestMapping(value = "/user-delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "redirect:/user-info";
	}
	
}

/*
 @Get 과 PostMapping을 한꺼번에 처리할 수 있는 방법 : @RequestMapping을 사용하여 method GET과 POST를 한 번에 묶어서 해결
 
 @RequestMapping : 사용자의 http 요청을 특정 메서드와 할 수 있도록 매핑하는(감싸주는) 역할을 한다.
 			 value = "" : value는 사용자가 요청할 때 주고받는 url을 작성해준다.
 			 사용법 : value = "/user-delete/{id}"
 			 이런 value값이 있을 때 {id}는 PathVarable(패스(경로)변수)로 실제 요청할 경우 해당 url 위치로 들어오는 값을 변수로 활용할 수 있다.
 			 
 			 method = "" : method에서 처리할 http 요청을 메서드에 지정.
 			 사용법 : RequestMethod.GET // RequestMethod.Post
 			 	RequestMethod.GET : HTTP GET 메서드는 주로 DB에 정보를 요청하기 위해 사용.
 			 						사이트에서 URL을 통한 직접 요청이나 링크를 클릭해야 하는 상황에서 GET 메서드가 사용되며,
 			 						데이터를 서버로 전송하지는 않고 주로 데이터를 요청하거나 조회할 때만 사용할 수 있다.
 			   RequestMethod.Post : 주로 서버로 데이터를 제출하기 위해 사용.
 			   						데이터를 HTML 본문에 담아 전송하기 때문에 GET 보다 대량의 데이터를 처리하기에 적합하다.
 
 @PathVariable : 경로에 대한 변수를 메서드의 매개변수로 받을 때 사용
 		  사용법 : @PathVariable int id
 		  
 @ModelAttribute("값") : Thymeleaf View에서 설정한 값의 이름을 사용하여 Model 속성에 엑서스할 수 있음
 						*엑서스 : 컴퓨터 데이터 또는 리소스를 어떤 방식으로든 사용할 수 있도록 권한을 주거나 권한이 담겨진 것을 의미 
 		  사용법 : @ModelAttribute("user") : user 이라는 이름으로 Model에 User 객체를 추가한 것
 
 @Validated : 데이터 유효성 검사를 실시하도록 행하는 것
 		  사용법 : @Validated(user) : User 객체에 대한 데이터 유효성 검사를 실시하겠다라는 것
 
 BindingResult : @Validated에서 실시한 유효성 결과 검사를 저장하는 객체로 유효성 검사에서 발생한 오류에 대한 정보가 담기는 공간
 */
