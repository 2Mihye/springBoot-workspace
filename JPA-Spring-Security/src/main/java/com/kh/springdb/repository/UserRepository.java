package com.kh.springdb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.SecurUser;

public interface UserRepository extends JpaRepository<SecurUser, Long>{
	// 로그인을 위한 옵션활용 repository 생성
	Optional<SecurUser> findByusername(String username);
}
