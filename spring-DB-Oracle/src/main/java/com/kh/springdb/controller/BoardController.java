package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.Board;
import com.kh.springdb.service.BoardService;

@Controller
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public String getAllBoards(Model model) { // 게시물 전체 보는 controller
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "board-list";	
	}
	
	@GetMapping("/{boardID}") // 이 주소는 원래 /boards/{boardID}로 앞에있는 boards가 내장되어 /{boardID}로 쓴다.
	public String getBoardById(@PathVariable int boardID, Model model) {
		Board board = boardService.getBoardById(boardID);
		model.addAttribute("board", board);
		return "board-detail";
	}
	
	@GetMapping() // GetMapping을 사용하여 게시물 작성하는 HTML로 이동한 후 
	public String 메서드명 {
		
	}
	
	@PostMapping() // PostMapping을 사용하여 작성해놓은 insert HTML Form을 가져온다
	public String () {
		
	}
}
