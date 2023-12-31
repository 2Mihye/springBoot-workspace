package com.kh.oracleDB.mallBoard.Controller;

import java.io.IOException;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.oracleDB.mallBoard.model.Item;
import com.kh.oracleDB.mallBoard.service.ItemService;

import lombok.RequiredArgsConstructor;

/**************************************************** 4 *****************************************************/

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	
	// 메인페이지
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Item> items = itemService.allItemView();
		model.addAttribute("items", items);
		return "index";
	}
	
	// 상품 전체 목록 페이지로 이동하기 위한 GetMapping
	@GetMapping("/item/list")
	public String itemList(Model model) {
		List<Item> items = itemService.allItemView();
		model.addAttribute("items", items);
		return "itemList";
	}
	
	
	// 상품 등록 페이지로 이동하기 위한 GetMapping
	@GetMapping("/item/new")
	public String itemSaveForm(Model model) {
		return "addItemForm";
	}
	
	// 클라이언트가 등록한 상품 등록 내용을 DB에 업로드
	@PostMapping("/item/new")
	public String itemSave(Item item, MultipartFile imgFile) throws IllegalStateException, IOException {
		itemService.saveItem(item, imgFile); // throws
		return "redirect:/item/list";
	}
	
	// 상품 상세보기 메서드
	@GetMapping("/item/itemdetails/{id}")
	public String itemDetails(Model model, @PathVariable("id") int id) {
		Item item = itemService.getItemById(id);
		model.addAttribute("item", item);
		return "itemDetails";
	}
}
