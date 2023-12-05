package com.kh.oracleDB.mallBoard.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.oracleDB.mallBoard.model.vo.Item;
import com.kh.oracleDB.mallBoard.service.ItemService;

import lombok.*;

@Controller
@RequiredArgsConstructor // @NotNull로 표시된 필드를 사용하여 생성자를 생성
public class ItemController {
	private final ItemService itemService;
	
	// 로그인을 안 한 경우
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Item> items = itemService.allItemList();
		model.addAttribute("items", items);
		return "/index"; // 오직 view html 과 연결하기 위해서 작성되는 페이지
	}
	
	// 상품 등록 페이지 - 판매자만 가능
    @GetMapping("/item/new")
    public String itemSaveForm(Model model) {
            return "itemForm";
      
    }
    
	// 상품 등록 (POST) - 판매자만 가능
	@PostMapping("/item/new") // @PostMapping을 사용하여 상품 등록으로 입력된 값을 DB로 보내기
	public String saveItem(Item item, MultipartFile photoFile) throws Exception { // MultipartFile을 이용하여 상품을 등록할 때 이미지 파일도 같이 등록될 수 있도록 파라미터 생성
		// itemService.addItem(item); // 이미지 없이 상품을 등록하고 싶다면 item만 작성해도 되지만 이미지 또한 포함해서 상품을 등록하고 싶다면 item에 photoFile을 추가해서 작성
		itemService.addItem(item, photoFile);
		return "redirect:/index";
	}
    
    
	/*
	@GetMapping("/item/list")
	public String itemList(Model model, @PageableDefault(size = 12) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
		// 페이지네이션 처리를 위한 서비스
		// 검색을 하지 않고 페이징처리를 원함
		// Page<Item> items = itemService.getItemByPage(pageable);
		return "itemList";
	}
	// @GetMapping을 사용하여 상품 등록페이지 만들기 // admin만 등록할 수 있게 수정
	@GetMapping("/new")
	public String addItemForm(Model model) {
		return "addItemForm.html";
	}
	*/

	
	// 상세
	@GetMapping("/view/{id}")
	public String viewItem(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("item", itemService.getItemById(id));
		return "viewItem";
	}
	
	// 수정
	
	
	// 삭제
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Integer id) {
		itemService.itemDelete(id);
		return "itemList";
	}
}
