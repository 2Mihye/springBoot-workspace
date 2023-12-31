package com.kh.oracleDB.mallBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracleDB.mallBoard.model.vo.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// 유저 이름 조회
	User findByUserName(String userName);
	
	// 유저 아이디 찾기
	User findById(int id);
}
