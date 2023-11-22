package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.Board;
import com.kh.springdb.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	public List<Board> getAllBoards(){
		return boardMapper.getAllBoards();
	}
}
