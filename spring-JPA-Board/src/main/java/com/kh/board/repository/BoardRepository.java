package com.kh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.board.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	// query를 적지 않는 이유는 이미 findAll 또는 findById 같은 것들이 내장되어있기 떄문
}
