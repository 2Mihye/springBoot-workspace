package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.vo.StMember;
public interface StMemberRepository extends JpaRepository <StMember, Long>{
	
}
