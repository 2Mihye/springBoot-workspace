package com.kh.cafe.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafe.service.CafeService;
import com.kh.cafe.vo.Cafe;

@Controller
@RequestMapping("/cafes")
public class CafeController {
	private final CafeService cafeService;
	
	@Autowired
	public CafeController(CafeService cafeService) {
		this.cafeService = cafeService;
	}
	
	// 전체보기
	@GetMapping
	public String getAllCafes(Model model, @RequestParam(required=false)String cafeName) { 
		// 카페 리스트를 만들어 준 후 만약 리스트에서 카페가 존재한다면 그 카페 목록들만 보여주고, 만야 존재하지 않는다면  그냥 모든 카페 내용을 보여주곘다.
		List<Cafe> cafes;
		if(cafeName != null && !cafeName.isEmpty()/*만약 카페명이 빈 값이 아니라거나 null값이 아니라면 사람들이 검색한 카페 내용을 service에서 가져와 뿌린다음 cafes에 넣어버리는 것*/) {
			cafes = cafeService.findCafes(cafeName);
		} else {
			// 모든 카페 리스트를 보여주겠다.
			cafes = cafeService.getAllCafes();
		}
		model.addAttribute("cafes", cafes);
		return "cafes";
	}
	
	/*
	 @RequestParam(required=false) : 파라미터 값을 필수로 적어주지 않아도 됨을 나타냄.
	 
	 @RequestParam : HTTP 자체에서 요청으로 안에서 파라미터를 메서드의 매개변수로 전달할 때 사용.
	 				 클라이언트가 웹 애플리케이션에 보애는 요청의 파라미터 값을 받아서 처리하는 데 사용.
	 
	 @PathVariable과 @RequestParam의 차이점
	 		@PathVariable : URL 경로에서 변수 값을 추출. 예시) url/cafes/{id}
	 		@RequestParam : 한 경로 안에서 클라이언트가 요청한 파라미터 값을 추출. 예시) url/cafes?cafeName=사용자가 폼에 입력한 값
	 */
	
	// 상세보기
	@GetMapping("/detail/{cafeID}")
	public String getCafeByID(@PathVariable Long cafeID, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeByID(cafeID);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafeDetail";
	}
	
	// 카페 저장
	@GetMapping("/new")
	public String showCafeForm(Model model) {
		model.addAttribute("cafe", new Cafe());
		return "cafeForm";
	}
	@PostMapping("/save")
	public String saveCafe(@ModelAttribute Cafe cafe) {
		cafeService.saveCafe(cafe);
		return "redirect:/cafes";
	}
	
	
	// 카페 수정
	@GetMapping("/update/{cafeID}")
	public String updateCafe(@PathVariable Long cafeID, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeByID(cafeID);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafeForm";
	}
	
	
	// 카페 전체 삭제
	@GetMapping("/delete/all")
	public String deleteAllCafes() {
		cafeService.deleteAllCafes();
		return "redirect:/cafes";
	}
	
	// 카페 삭제
	@GetMapping("/delete/{cafeID}")
	public String deleteCafe(@PathVariable Long cafeID) {
		cafeService.deleteCafeByID(cafeID);
		return "redirect:/cafes";
	}
	
	/*↓↓메인페이지에서 검색할 수 있게 getAllCafes() 와 합쳐줌↓↓
	@GetMapping("/search")
	public String searchCafes(@RequestParam String keyword, Model model) {
		// 특정 키워드를 포함하는 카페를 검색
		List<Cafe> cafes = cafeService.findCafes(keyword);
		// model에 검색 결과 추가
		model.addAttribute("cafes", cafes);
		// 검색 결과를 보여줄 뷰 페이지 작성
		return "searchResults";
	}
	*/
	
	// 지역카운터
	@GetMapping("/count/{location}")
	public String countCafesByLocation(@PathVariable String location, Model model) {
		int cafeCount = cafeService.countCafesByLocation(location);
		//1.지역값을 저장할 모델
		model.addAttribute("location", location);
		//2.지역 갯수를 저장할 모델
		model.addAttribute("cafeCount", cafeCount);
		return "cafeCount";
	}
	
	/*
	@GetMapping("/count")
	public String countCafesByLocation(@RequestParam String location, Model model) {
		int cafeCount = cafeService.countCafesByLocation(location);
		//1.지역값을 저장할 모델
		model.addAttribute("location", location);
		//2.지역 갯수를 저장할 모델
		model.addAttribute("cafeCount", cafeCount);
		return "cafeCount";
	}
	*/
	
	//카페 존재여부
	@GetMapping("/exists/{name}")
	public String existsCafeByName(@PathVariable String name, Model model) {
		boolean cafeExists = cafeService.existsCafeByName(name);
		model.addAttribute("cafeExists", cafeExists);
		return "cafeExists";
	}
}
