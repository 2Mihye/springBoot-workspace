package com.kh.oracleDB.mallBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracleDB.mallBoard.model.Cart;
import com.kh.oracleDB.mallBoard.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	List<CartItem> findCartItemByItemId(int id);
	CartItem findCartItemById(int id);
	
	List<CartItem> findByItemId(int itemId); // itemId로 장바구니에서 상품 넣거나 찾을 수 있도록 ItemId를 통해 조회하는 아이템을 집어넣을 수 있게 해주는 메서드
	
	CartItem findByCartIdAndItemId(Long cartId, int itemId);
}
