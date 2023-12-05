package com.kh.oracleDB.mallBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracleDB.mallBoard.model.vo.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	CartItem findByCartIdAndItemId(int cartId, int itemId); // cartID 와 itemID로 cartItem을 찾는 메서드
	
	CartItem findCartItemById(int id); // ID 값에 해당하는 아이템을 찾는 메서드
	
	List<CartItem> findCartItemByItemId(int id); // cart에 담겨있는 모든 item을 반환하는 리스트
	
}
