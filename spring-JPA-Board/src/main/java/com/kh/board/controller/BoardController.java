package com.kh.board.controller;

import java.util.Optional;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.board.service.BoardService;
import com.kh.board.vo.Board;

@Controller // 뷰를 불러올 수 있음
@RequestMapping("/boards")
public class BoardController {
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping
	public String getAllBoards(Model model) {
		model.addAttribute("boards", boardService.getAllBoards());
		return "boards";
	}
	
	@GetMapping("/detail/{boardID}")
	public String getBoardByID(@PathVariable Long boardID, Model model) { // 게시물 상세보기 컨트롤러 // Optional은 항상 java.util.Optional로 import
		Optional<Board> board = boardService.getBoardByID(boardID);
		board.ifPresent(value -> model.addAttribute("board", value)); // ifPresent는 람다식을 쓸 수 있다면 쓰겠다는 메서드
		return "board_detail";
	}
	
	// insert를 위한 Get Post Mapping 작성
	@GetMapping("/new")
	public String displayBoardForm(Model model) {
		model.addAttribute("board", new Board());
		return "board_form";
	}
	
	@PostMapping("/save")
	public String saveBoard(@ModelAttribute Board board) {
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	
	// 수정하기
	@GetMapping("/update/{boardID}")
	public String getUpdateBoard(@PathVariable Long boardID, Model model) {
		Optional<Board> board = boardService.getBoardByID(boardID);
		board.ifPresent(value -> model.addAttribute("board", value));
		return "board_form";
	}
}
