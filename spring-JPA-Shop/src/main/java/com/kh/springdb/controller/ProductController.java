package com.kh.springdb.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springdb.service.ProductService;
import com.kh.springdb.vo.Products;

@Controller
// @RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public String getAllProducts(Model model){ // 모든 제품을 보기 위한 제품 List 확인 메서드
		List<Products> products = productService.getAllProducts();
		model.addAttribute("products", products);
		// return productService.getAllProducts();
		return "products"; // html명
	}
	
	@GetMapping("/detail/{id}")
	public String getProductByID(@PathVariable Long id, Model model) { // 제품 상세보기 메서드
		Optional<Products> product = productService.getProductByID(id);
		product.ifPresent(value -> model.addAttribute("product",value));
		return "product_detail";
	}
	
	// 작성한 내용을 저장하기 위한 메서드
	@GetMapping("/new") // save는 GetMapping과 PostMapping을 써야함
	public String displayProductForm(Model model) {  // 작성할 URL을 불러오기 위한 주소값 설정
		model.addAttribute("products", new Products());
		return "product_form";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Products product) { // 작성한 내용을 저장할 URL 설정
		productService.saveProduct(product);
		return "redirect:/products";
	}
	
	@GetMapping("/delete/{id}")	// delete는 GetMapping만 씀
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProducByID(id);
		return "redirect:/products";
	}
}

/*
@Controller와 @RestController
@Controller : 
	어노테이션이 부착된 전통적인 SpringMVC 패턴을 적용한 것으로 view를 생성하고 반환하는 역할을 하기도 함.
	주로 @RequestMapping과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄.
	데이터를 반환할 때는 Model 객체를 통해 View에 데이터를 전달함.
	최종적으로 return하는 것이 html 파일.
	
@RestController :
	DB를 바로 리턴해서 상호작용하도록 바꿔주는 녀석.
	조금 더 RESTful 웹 서비스를 제공하는 데 특화된 어노테이션으로 @Controller에 @ResponseBody를 함께 사용한 것과 유사하게 동작함.
	@RestController은 이런 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션.
	
	*	주로 @Controller은 View(html 파일)을 반환하는 데 사용되고,
		@RestController은 @Controller에 @ResponseBody를 추가로 사용하는 것을 대체할 수 있어서 코드가 조금 더 간결해짐.
		
@ResponseBody :
	메서드가 return해서 반환 해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌 java 코드에서 직접 본문으로 전달해서 사용할 수 있는 어노테이션.
*/
