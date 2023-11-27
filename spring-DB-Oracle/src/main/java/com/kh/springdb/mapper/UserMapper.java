package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.User;

@Mapper
public interface UserMapper { // 클래스가 아닌 interface로 !

	List<User> getAllUsers(); // 모든 유저 조회

	User getUserById(int id); // 유저 한 명 조회

	void insertUser(User user);// insert는 db에 넣고 뿌려주지 않아서 void
	
	User loginUser(String memail);
	
	void updateUser(User user); //유저 정보 수정 메서드
	
	void deleteUser(int mno); // 유저 정보 삭제 메서드
}
