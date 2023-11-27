package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// GetMapping을 사용하여 게시물 작성하는 HTML로 이동한 후 
	@GetMapping("/create") // HTTP GET 요청이 /create 라는 경로로 들어올 때 호출
	public String displayCreateForm (Model model) { // Model 객체를 매개변수로 받아서 template(view)로 데이터를 전달할 수 있다.
		model.addAttribute("board", new Board()); //  new Board로 새로운 Board객체를 생성하여 model에 추가
		return "board-form"; // 나중에 만들 board-form.html 템플릿에서 해당 뷰를 보여줌
	}
	
	@PostMapping("/create") // PostMapping을 사용하여 작성해놓은 insert HTML Form을 가져온다
	public String createBoard(@ModelAttribute Board board) {
		boardService.insertBoard(board);
		return "redirect:/boards"; // 글이 작성된 후 돌아갈 template 작성
	}
	
	@PostMapping("/update/{boardID}") // 작업하는 공간
	public String UpdateForm(@PathVariable int boardID, @ModelAttribute Board board) {
		// URL에서 가져온 boardID 값을 Board 객체에 저장
		board.setBoardID(boardID);
		boardService.updateBoard(board);
		
		// 수정이 완료된 후 게시글 목록 페이지로 돌아가기
		return "redirect:/boards";
	}
	
	@GetMapping("/update/{boardID}")
	public String displayUpdateForm(@PathVariable int boardID, Model model) {
		Board board = boardService.getBoardById(boardID);
		model.addAttribute("board", board);
		return "board-form";
	}
	
	@GetMapping("delete/{boardID}")
	public String deleteBoard(@PathVariable int boardID) {
		boardService.deleteBoard(boardID);
		return "redirect:/boards";
	}
	
	// 게시물 모두 삭제
	@GetMapping("/delete-all-boards")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
	
	/*
	 *Model model : 컨트롤러에서 뷰로 데이터를 전달할 떄 사용하는 인터페이스
	 *				컨트롤러에 있는 메서드에서 매개변수로 Model을 선언하면 GET에 추가한 데이터는 자동으로 뷰에 전달
	 *				SELECT에서 DB에서 담겨온 데이터는 자동으로 Model에 담겨 View(html) 파일로 전달 됨
	 * @ModelAttribute 클래스 이름 클래스를 대신할 변수명
	 * 					Form 에디터나 URL 경로에서 전달된 데이터를 객체에 넣어줄 때 사용
	 * 					클라이언트에서 전송한 데이터를 객체로 값을 넣어주고 컨트롤러에서 사용할 수 있도록 해주는 것
	 * 					전달된 데이터는 mapper를 통해 DB에 저장된다.
	 */
}
