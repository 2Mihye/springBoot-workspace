package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.springdb.mapper.BoardMapper;
import com.kh.springdb.model.Board;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	public List<Board> getAllBoards(){ // 게시판에서 게시물 전체보기 서비스
		return boardMapper.getAllBoards();
	}
	
	public Board getBoardById(int boardID) { // 게시판에서 게시물 1개 선택했을 때 1가지에 대한 상세보기 서비스
		return boardMapper.getBoardById(boardID);
	}
	
	public void insertBoard(Board board){ // 게시판에거 게시글 작성하기 서비스
		boardMapper.insertBoard(board);
	}
	
	public void updateBoard(Board board) { // 게시판에서 게시글 수정하기 서비스
		boardMapper.updateBoard(board);
	}
	
	public void deleteBoard(int boardID) { // 게시판에서 게시글 삭제하기 서비스
		boardMapper.deleteBoard(boardID);
	}
	
	@Transactional
	public void deleteAllBoards() { // 게시물 모두 삭제
		boardMapper.deleteAllBoards();
	}
	
	/*
	 @Transactional : 트랜잭션을 지원하는 스프링에서 DB 관리를 단순히 어노테이션을 사용하여 여러 개의 DB 조작 작업을 묶어서 하나의 작업 단위로 처리하는 데 사용하며
	 				  작업은 성공 혹은 실패로 완료될 수 있다.
	 				  이 코드를 사용하면 개발자가 일일이 커밋 또는 롤백을 관리하는 코드를 작성하지 않아도 된다.
	 				  
	 */
}
