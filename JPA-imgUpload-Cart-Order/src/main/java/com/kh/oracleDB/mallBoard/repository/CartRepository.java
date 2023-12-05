package com.kh.oracleDB.mallBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracleDB.mallBoard.model.vo.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	Cart findByUserId(int id); // 사용자 ID를 바탕으로해서 ID 주인의 카트를 조회하기 위해 사용하는 메서드
	Cart findCartById(int id); // 주어진 cart ID를 바탕으로 하여 Cart 내용 조회
	Cart findCartByUserId(int id); // cart에서 cart를 중심으로 userID를 검색해서 조회
	
}