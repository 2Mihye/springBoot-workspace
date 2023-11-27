package com.kh.springdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kh.springdb.model.Board;
import com.kh.springdb.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("boardList")
	public String displayBoardList(Model model) {
		model.addAttribute("boards", boardService.getAllBoards());
		return "boardList";
	}
	
	@GetMapping("board-info/{id}")
	public String getBoardById(@PathVariable int id, Model model) {
		Board board = boardService.getBoardById(id);
		model.addAttribute("board", board);
		return "board-info";
	}
	
	@GetMapping("/post")
	public String insertBoard(Model model) {
		model.addAttribute("board", new Board());
		return "post";
	}
}
