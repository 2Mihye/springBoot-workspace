package com.kh.springdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springdb.vo.Product;

@RestController
public class StartController {
	@GetMapping(value = "/test")
	public Product startProduct() {
		Product pd = new Product();
		pd.setProduct_id(2);
		pd.setProduct_name("kh");
		return pd;
	}
}

/*
	@Controller와 @RestController
	@Controller : 
		어노테이션이 부착된 전통적인 SpringMVC 패턴을 적용한 것으로 view를 생성하고 반환하는 역할을 하기도 함.
		주로 @RequestMapping과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄.
		데이터를 반환할 때는 Model 객체를 통해 View에 데이터를 전달함.
		
	@RestController : 
		조금 더 RESTful 웹 서비스를 제공하는 데 특화된 어노테이션으로 @Controller에 @ResponseBody를 함께 사용한 것과 유사하게 동작함.
		@RestController은 이런 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션.
		
		*	주로 @Controller은 View(html 파일)을 반환하는 데 사용되고,
			@RestController은 @Controller에 @ResponseBody를 추가로 사용하는 것을 대체할 수 있어서 코드가 조금 더 간결해짐.
			
	@ResponseBody :
		메서드가 return해서 반환 해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌 java 코드에서 직접 본문으로 전달해서 사용할 수 있는 어노테이션.
 */
