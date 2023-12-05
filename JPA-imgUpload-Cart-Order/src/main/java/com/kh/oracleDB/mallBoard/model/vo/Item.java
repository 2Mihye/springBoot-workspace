package com.kh.oracleDB.mallBoard.model.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
	      
	// PrimaryKey
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
	@SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
	private int id; // 
	private String name; // 상품이름
	private String description; // 상품 설명
	private int price; // 상품 가격
	private int count; // 판매 개수
	private int stock; // 재고
	private int isSoldOut; // 상품 상태 (0 : 판매중 || 1 : 품절)
	//@ManyToOne // 판매자 한명이 여러개의 상품을 팔 수 있기 때문에 (판매자 1: 상품 N)
	//@JoinColumn(name="admin_id")
	// private Admin admin; // 판매자 ID -> 상품을 판매하는 판매자가 여러명일 수 있으므로 판매자가 누구인지 아이디를 넣어줘야 함.
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id")
	private User seller; // 판매자 아이디
	
	@OneToMany(mappedBy = "item")
	private List<CartItem> cartItems = new ArrayList<>();
	
	private String photoName; // 이미지 파일명
	private String photoPath; // 이미지 조회 경로
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate; // 상품 등록 날짜
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
	// private List<CartItem> cart_items = new ArrayList<>();
	
}
