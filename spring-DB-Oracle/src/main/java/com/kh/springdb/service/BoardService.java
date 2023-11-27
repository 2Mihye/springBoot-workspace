package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void saveBoard(Board board){ // 게시판에거 게시글 작성하기 서비스
		boardMapper.saveBoard(board);
	}
	
	public void updateBoard(Board board) { // 게시판에서 게시글 수정하기 서비스
		boardMapper.updateBoard(board);
	}
	
	public void deleteBoard(int boardID) { // 게시판에서 게시글 삭제하기 서비스
		boardMapper.deleteBoard(boardID);
	}
}
