package com.kh.springdb.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="products_seq")
	@SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 1)
	private int id;
	
	private String name;
	private String text;
	private String price;
	private int count;
	private int stock;
	
	// private boolean isCheckOut;
	private int isSoldOut;
	
	
	// 댓글 작성을 위한 Comment 생성
	@OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
	private List<Comments> comments;
	
	
	// 상품 이미지를 위한 필드 설정
	private String imgName;
	private String imgPath;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
	@PrePersist
	public void createDate() { // 자동으로 날짜 생성
		this.createDate = LocalDate.now();
	}
	
	// 제품에 대한 좋아요를 받고 싶다면 여기에 추천과 관련된 변수를 넣어줘도 됨.
	
}
