package com.kh.springdb.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.service.StMemberService;
import com.kh.springdb.vo.StMember;

@Controller
@RequestMapping("/members")
public class StMemberController {
	
	private StMemberService stService;
	
	@Autowired
	public StMemberController(StMemberService stService) {
		this.stService = stService;
	}
	
	@GetMapping
	public String getAllMember(Model model) {
		model.addAttribute("members", stService.getAllMember());
		return "members";
	}
	

	// insert문 작성
	@GetMapping("/new")
	public String showMemberForm(Model model) {
		model.addAttribute("member", new StMember());
		return "memberForm";
	}
	
	@PostMapping("/save")
	public String saveMember(@ModelAttribute StMember stMember) {
		stService.saveMember(stMember);
		return "redirect:/members";
	}
	
	// update문 작성
	@GetMapping ("/update/{MemberId}")
	public String updateMember(@PathVariable Long id, Model model) {
		// 옵셔널 이용해서 id값 가지고오기
		Optional<StMember> member = stService.getMemberById(id);
		member.ifPresent(value -> model.addAttribute("member", value)); // 람다식 사용하여 member 값을 추가하는 코드 작성
		// service 에 findById 관련 메서드 작성하기
		
		return "memberForm";
	}
	
	// delete문 작성
	@GetMapping ("/delete/{MemberId}")
	public String deleteMember(@PathVariable Long id) {
		stService.deleteMemberById(id);
		return "redirect:/members";
	}
	
}
