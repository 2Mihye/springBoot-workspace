package com.kh.springdb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByUserName(String userName);
}
