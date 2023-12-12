package com.kh.springdb.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Product;
import com.kh.springdb.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	
	public List<Product> allProductView(){
		return productRepository.findAll();
	}
	
	public void saveProduct(Product product, MultipartFile imgFile) throws Exception {
		// 이미지 파일 이름 가져오기
		String originName = imgFile.getOriginalFilename();
		
		String  projectPath = System.getProperty("user.dir") + "/src/main/resource/static/img/";
		
		File saveFile = new File(projectPath, originName);
		
		imgFile.transferTo(saveFile); // MultipartFile 에 File로 읽어온 이미지 파일을 저장하기 위해 변환하기	
		
		product.setImgName(originName); // 가져온 파일 이름 원본 저장
		product.setImgPath(projectPath); // 경로 저장을 DB에 작성해주기
		
		productRepository.save(product);		
	}
	
	// 상품 상세 페이지나 수정하기 위해 아이디를 가져와서 상품을 보여주거나 수정할 수 있도록 가져오는 메서드
	public Product getProductById(int id) {
		return productRepository.findProductById(id);
	}
}
