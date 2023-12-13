package com.kh.springdb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	// 로그인을 하기 위해 검색하는 코드 (Optional 이나 Public으로 orElse를 사용해서 만들 수도 있음)
	Optional<Users> findByUserName(String userName);
}
