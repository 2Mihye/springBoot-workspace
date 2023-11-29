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
	
	@Autowired // 초기화
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
	
	// 작성한 내용을 저장하기 위한 메서드 1. 최초로 작성한 내용 저장 2. 기존에 작성한 내용 수정해서 저장
	@GetMapping("/new") // save는 GetMapping과 PostMapping을 써야함
	public String displayProductForm(Model model) {  // 작성할 URL을 불러오기 위한 주소값 설정
		model.addAttribute("products", new Products());
		return "product_form";
	}
	
	@PostMapping("/save") 
	public String saveProduct(@ModelAttribute Products product) { // 작성한 내용을 저장할 URL 설정
		productService.saveProduct(product); // Service를 불러서 view가 전달해달라는 데이터 전달
		return "redirect:/products";
	}
	
	@GetMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		Optional<Products> product = productService.getProductByID(id);
		product.ifPresent(value -> model.addAttribute("product", value));
		return "product_form";
	}
	
	@GetMapping("/delete/{id}")	// delete는 GetMapping만 씀
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProducByID(id);
		return "redirect:/products";
	}
}

/*
Path : 개발자들이 개발하기 위해 URL 대신에 사용하는 위치 경로
	   URL보다 상위개념
	   
	  
Optional<Products> product = productService.getProductByID(id);
	Optional 안에는 productService.getProductByID(id) 로 id 값을 가져와서 id에 해당하는 제품을 가져오는데, 만약 id에 해당하는 제품이 존재하지 않으면
	비어있게 된다.
	만약 Optional이 비어있게 된다면 에러가 발생할 수 있지만(현재), 추후 비어있을 경우를 대비하여 예외 값을 처리해주는 것이 좋다.
	
	예외값 처리하는 방법 : orElse 를 이용하여 대체값을 제공하거나 페이지 이동 처리를 할 수 있다. (EX/ error.html)
					 이외에 대체값을 생성하는 함수인 orElseGet / 예외를 던지는 orElseThrow 도 어디에서 어떻게 처리할지 제외하고 거의 셋 다 똑같음.
product.ifPresent(value -> model.addAttribute("product", value));
	ifPresent: Optional 객체 안에 값이 존재할 경우 람다식 표현을 실행하기 위한 메서드.
			   value 값이 존재하면 model에 product 변수명을 사용해서 product 안에 value 값을 추가할 것.
			   추가된 product는 html template 안에서 product를 thymeleaf를 통해 호출해서 value값을 사용할 수 있다.
		 *람다식 : 간결하게 함수를 표현하는 방법으로 간단하게 결과를 표현할 때 사용.
		 		 기본 코드는 (변수값) -> 변수값이 존재하거나 어떤 일이 발생할 경우의 결과를 작성.
	

@Controller와 @RestController
@Controller : 
	어노테이션이 부착된 전통적인 SpringMVC 패턴을 적용한 것으로 view를 생성하고 반환하는 역할을 하기도 함.
	주로 @RequestMapping과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄.
	view 템플릿 안에 들어있는 html과 상호작용 할 수 있도록 제어하는 컨트롤러.
	데이터를 반환할 때는 Model 객체를 통해 View에 데이터를 전달함.
	최종적으로 return하는 것이 html 파일.
	
@RestController :
	DB를 바로 리턴해서 상호작용하도록 바꿔주는 녀석.
	조금 더 RESTful 웹 서비스를 제공하는 데 특화된 어노테이션으로 @Controller에 @ResponseBody를 함께 사용한 것과 유사하게 동작함.
	@RestController은 이런 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션.
	DB에서 받은 내용을 출력하거나 우리가 지정한 값을 화면에 보여줄 수 있도록 하는 컨트롤러.
	
	*	주로 @Controller은 View(html 파일)을 반환하는 데 사용되고,
		@RestController은 @Controller에 @ResponseBody를 추가로 사용하는 것을 대체할 수 있어서 코드가 조금 더 간결해짐.
		
@ResponseBody :
	메서드가 return해서 반환 해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌 java 코드에서 직접 본문으로 전달해서 사용할 수 있는 어노테이션.
*/
