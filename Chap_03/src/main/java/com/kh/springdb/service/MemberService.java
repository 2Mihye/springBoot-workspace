package com.kh.springdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.vo.MemberVO;
import com.kh.springdb.model.vo.dao.MemberDAO;

@Service
public class MemberService {
	private MemberDAO memberDAO;
	
	@Autowired
	
	// 전체보기
	
	
	/*
	// 멤버에 대한 정보 상세보기
	public MemberVO getMemberById(int memberId) {
		return memberDAO.getMemberById(memberId);
	}*/
	
	// 삽입하기
	public void insertMember(MemberVO member) {
		memberDAO.insertMember(member);
	}
	
	
	// 수정하기
	
	// 삭제하기
}
