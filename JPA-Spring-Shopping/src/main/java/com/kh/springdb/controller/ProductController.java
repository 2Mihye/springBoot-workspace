package com.kh.springdb.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Product;
import com.kh.springdb.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping("/")
	public String mainPageView(Model model) {
		return "index";
	}
	
	// 상품 전체 목록 페이지로 이동하기 위한 GetMapping
	@GetMapping("/product/list")
	public String productList (Model model) {
		// 아이템을 추가한 서비스를 불러와서 모델에 넣어주기
		List<Product> products = productService.allProductView();
		model.addAttribute("products", products);
		return "productList";
	}
	
	// 상품 등록 페이지 - 조회
	@GetMapping("/product/new")
	public String productSaveForm(Model model) {
		model.addAttribute("product", new Product());
		return "addProductForm";
	}
	
	// 등록된 상품 업로드 페이지
	@PostMapping("/product/new")
	public String productSave(Product product, MultipartFile imgFile) throws Exception {
		productService.saveProduct(product, imgFile);
		return "redirect:/"; // 상품 리스트 페이지로 변경해서 상품 등록 후 이동하는 경로를 바꿔줄 수 있음.
	}
	
	// 상품 클릭했을 때 상세보기 메서드
	@GetMapping("/product/detail/{id}")
	public String productDetail(@PathVariable("id") int id, Model model) {
		// 상세보기를 검색할 조건
		Product product = productService.getProductById(id);
		// 하나의 아이디 값을 가져와서 지정된 제품의 모든 내용을 보여줄 수 있도록 함
		//				   "product" template 밑에서 thymeleaf로 불러올 변수명을 product로 지정. Product product로 만들어준 필드명을 가져와서 service로 불러온 내용을 "product"안에 지정해준다는 의미.			   
		model.addAttribute("product", product);
		return "product_detail";
	}
}
