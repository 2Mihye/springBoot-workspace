package com.kh.oracleDB.mallBoard.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class Cart {
	// 비회원에게 ID를 만들어 넣어주기 & 쿠키를 사용하기로 장바구니 이용가능
	// 비회원 ID값 비회원이 주문한 장바구니 리스트
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
	private Long id;
	
	@OneToMany(mappedBy = "cart", cascade=CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();
	
	
	// order 객체 생성으로 인한 추가 mapper
	// 만약 Entity에 설정한 이름이 있다면 
	// @JoinColumn(name="customer_order_id")
	@OneToOne(mappedBy = "cart")
	@JoinColumn(name="order_id")
	private Orders order;
	
	
	
	public int getTotalAmount() {
		return cartItems.stream().mapToInt(item -> item.getCount() * Integer.parseInt(item.getItem().getPrice())).sum();
	}
	
	// stream() : 리스트로 받아서 스트림으로 변환하겠다 (List나 Map으로 배열처리를 해서 총 가격 합을 받아야 하지만 Stream을 사용하여 List나 Map 대신에 한번에 받을 수 있도록 처리해주는 메서드
	// mapToInt(CartItem::getCount) : CartItem 객체를 int로 감싸주는 작업을 해서, CartItem에서 getCount 메서드를 호출하여 각각 CartItem에 연결된 count값을 가져오고 이 값을 int로 감싸주는 역할을 함.
	// 								  int로 감싸진 count 값을 sum이라는 메서드를 활용하여 모두 더해주기로 선언
	public int getTotalCount() {
		return cartItems.stream().mapToInt(CartItem::getCount).sum();
	}
	
	/*
	유저용
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
	private int id;
	
	private int count; // 카트에 담긴 총 상품 개수
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems = new ArrayList<>();// 카트에 담긴 상품 리스트를 넣기 위한 배열 생성
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
	
	public static Cart createCart() {
		Cart cart = new Cart();
		cart.setCount(0); // 장바구니에 상품 개수가 없기 때문에 0으로 초기화
		return cart;
	}*/
}
