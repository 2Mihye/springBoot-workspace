package com.kh.springdb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.ProductMapper;
import com.kh.springdb.model.Product;

/*bean : 스프링이 관리하는 자바 객체를 뜻 하고, 인스턴스화된 객체를 스프링 컨테이너에 등록하는 것을 스프링 빈이라고 한다.
    	 빈은 클래스의 등록된 정보, Getter/Setter 메서드를 가지고 있다.
    	 빈은 알아서 만들어지는 라이프 사이클이 있다.
 * repository : JPA에서 주로 사용함.
 * Entity : 생성된 DB에 접근하는 메서드들을 사용하기 위한 인터페이스
 * DI(Dipendency Injection 의존성 주입) : 프로그램에서 구성 요소에 의존하는 관계가 소스코드 내부가 아니라 외부에서 설정한 파일을 통해서 정의를 내리겠다는 의미.
*/
@Service
public class ProductService {
	/*
	 JPA
	 @Autowired // 의존관계를 주입할 때 사용하는 어노테이션으로 의존관계에 해당하는 빈을 찾아 주입하는 역할
	//private ProductRepository productRepository;
	*/
	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> getAllProducts(){
		//return productRepository.findAll();
		return productMapper.getAllProducts();//return 찾은 제품 모두 다 가지고 오는 메서드 추가;
	}
}
