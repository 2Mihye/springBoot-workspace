package com.kh.oracleDB.mallBoard.model.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItem_seq")
	@SequenceGenerator(name = "cartItem_seq", sequenceName = "cartItem_seq", allocationSize = 1)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private Item item;
	
	// 카트에 담긴 상품 갯수를 추가
	private int cartCount;
	
	public static CartItem createCartItem(Cart cart, Item item, int amount) {
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setItem(item);
		cartItem.setCartCount(amount);
		return cartItem;
	}
	
	public void addCartCount(int cartCount) {
		this.cartCount += cartCount;
	}
}


/*
 	@ManyToOne :
 		여러 엔티티가 하나의 엔티티에 감싸져서 활용되는 N:1 관계를 나타냄.
 	
 	(fetch = FetchType.EAGER) : 
 		매핑된(감싸진) 엔티티를 Java에서 검색할 때(찾고자 할 때) 바로 로딩해서 가져올 수 있도록 설정해준 것
 */
