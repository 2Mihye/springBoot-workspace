package com.kh.board.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 삭제하기
	@GetMapping("/delete/{boardID}")
	public String deleteBoard(@PathVariable Long boardID) {
		boardService.deleteBoard(boardID);
		return "redirect:/boards";
	}
	
	// 모두 삭제하기
	@GetMapping("/delete/all")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
	
	// 특정 키워드를 활용하여 게시물 검색하는 Mapping 메서드
	@GetMapping("/search")
	public String searchBoards(@RequestParam String keyword, Model model) {
		// 특정 키워드를 포함하여 게시물을 검색할 수 있도록 설정
		List<Board> boards = boardService.findBoardByTitle(keyword);
		
		// model에 검색결과 추가
		model.addAttribute("boards", boards);
		
		// 검색 결과를 보여줄 페이지 리턴
		return "searchResult";
	}
	
	/*
	 @RequestParam : Spring Framework에서 클라이언트로부터 전송된 HTTP 요청의 파라미터값을 받아오기 위해 사용되는 어노테이션
	 				 주로 웹 요청에서 쿼리 파라미터나 폼 데이터를 추출하는 데 사용.
	 				 클라이언트가 전송한 요청에 파라미터 값을 메서드에 매개변수로 받아올 때 사용.
	 				 예제) @GetMapping("/ex")
	 				 	  public String paramMethod(@RequestParam String name, @RequestParam int age){
	 				 	  		// name과 age는 클라이언트가 전송한 요청의 쿼리 파라미터 값
	 				 	  		return "View"; // Get일때는 보통 View로 리턴을 많이씀.
	 				 	  }
	 				 	  
	 				 	  
	 http://127.0.0.1:8082/board?keyword=키워드작성
	 localhost  127.0.0.1 = 내 아이피 주소(보호된 아이피 주소)
	 		    8082 = 포트번호
	 		    /board = /요청 경로(path) 특정 기능이나 페이지에 대한 요청을 나타냄.
	 		    ?keyword = ?는 쿼리의 시작을 나타내냄. DB 키 값을 작성
	 		    =키워드 =과 필요한 키워드를 넣은 것은 필요한 키워드 값을 작성하기
성	 */
}
