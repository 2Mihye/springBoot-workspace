package com.kh.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.board.repository.BoardRepository;
import com.kh.board.vo.Board;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> getAllBoards(){ // 게시물 전체 조회
		return boardRepository.findAll();
	}
	
	public Optional<Board> getBoardByID(Long id){ // 게시물 하나 조회
		return boardRepository.findById(id);
	}
	
	public Board saveBoard(Board board) { // 게시물 추가하기
		return boardRepository.save(board);
	}
}
