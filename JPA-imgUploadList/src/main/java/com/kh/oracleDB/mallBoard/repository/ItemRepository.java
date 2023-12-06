package com.kh.oracleDB.mallBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracleDB.mallBoard.model.Item;

/**************************************************** 2 *****************************************************/
public interface ItemRepository extends JpaRepository<Item, Integer>{
	// 상세보기나 수정하기를 위한 메서드 생성
	Item findItemById(int id);
	// 페이지네이션이나 검색을 위한 메서드
	
}
