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
	
	// 제품 좋아요 클릭하여 좋아요 수 추가
	private int likes; // 좋아요를 받는 방법은 여러 방법이 있음.
	 // 1. 사용자 관계 없이 카운트만 올라가게 하기
	
	 //	2. ManyToOne이나 OneToMany를 이용하여 서로 카운트 주기
		
	
}
