package com.kh.springdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kh.springdb.repository.StMemberRepository;
import com.kh.springdb.vo.StMember;

import jakarta.transaction.Transactional;

public class StMemberService {
	
	private final StMemberRepository stRepository;

	// 생성자
	@Autowired
	public StMemberService(StMemberRepository stRepository) {
		this.stRepository = stRepository;
	}
	
	// 전체조회, 값 입력, 수정, 삭제 (CRUD)
	// 1. 전체 조회
	public List<StMember> getAllMember(){
		return stRepository.findAll();
	}
	
	// 1-2 값 하나만 조회
	public Optional<StMember> getMemberById(Long id) {
		return stRepository.findById(id);
	}
	
	// 2. 값 입력 후 저장하는 메서드
	@Transactional
	public StMember saveMember(StMember stmember) {
		return stRepository.save(stmember);
	}
	
	// 3. 삭제하는 메서드
	public void deleteMemberById(Long id) {
		stRepository.deleteById(id);
	}
}
