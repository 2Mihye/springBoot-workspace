package com.kh.oracleDB.mallBoard.model.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItem_seq")
	@SequenceGenerator(name = "orderItem_seq", sequenceName = "orderItem_seq", allocationSize = 1)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Order order;
	
	// 구매자
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	private int itemId; // 상품 주문번호
	
	private String itemName; // 주문 상품이름
	
	private int itemPrice; // 주문한 상품 가격
	
	private int itemCount; // 주문 상품 수량
	
	private int itemTotalPrice; // 가격 * 수량 = 총 가격
	
	// 주문 상품에 매핑되는 판매상품
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "saleItem_id")
	private SaleItem saleItem; // 주문 상품에 매핑되는 판매상품
	
	// 주문 취소 여부 (1 : 주문 취소 || 0 : 주문완료)
	private int isCancel;
	
	// 장바구니 상품 하나씩 개별 주문
	public static OrderItem createOrderItem(int itemId, User user, Item item, int count, Order order, SaleItem saleItem) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setUser(user);
		orderItem.setOrder(order);
		orderItem.setItemName(item.getName()); // item의 이름을 그대로 가져와야 하므로 item.getName()
		orderItem.setItemPrice(item.getPrice()); // item의 가격을 그대로 가져와야 하므로 item.getPrice()
		orderItem.setItemCount(count); // 어떤 제품을 몇 개 살 것이고 최종적으로 몇 개를 구매할 것인지에 대한 카운트가 필요함.
		orderItem.setItemTotalPrice(item.getPrice() * count);
		orderItem.setSaleItem(saleItem);
		return orderItem;
	}
	
	// 장바구니 전체 주문
	public static OrderItem createOrderItem(int itemId, User user, CartItem cartItem, SaleItem saleItem) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setUser(user);
		orderItem.setItemName(cartItem.getItem().getName()); // cart 안에 있는 item에 대한 name을 가져옴
		orderItem.setItemPrice(cartItem.getItem().getPrice()); // cart 안에 있는 item을 가져오고, 그 item의 가격을 가져옴
		orderItem.setItemCount(cartItem.getCartCount());
		orderItem.setItemTotalPrice(cartItem.getItem().getPrice() * cartItem.getCartCount()); // cart 안에 있는 item의 가격을 가져와서 cart안의 갯수와 곱함으로 totalPrice 지정
		orderItem.setSaleItem(saleItem);
		return orderItem;
	}
	
}
