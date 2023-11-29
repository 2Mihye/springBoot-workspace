package com.kh.board.vo;

import jakarta.persistence.*;
import lombok.*;

// @GeneratedValue() 트리거를 만들어주는 어노테이션
@Entity // Entity 어노테이션이 이 클래스가 데이터와 연결되게 해주는 Entity
@Getter
@Setter
@Table(name = "Boards")
@SequenceGenerator(name = "board_add_sequence", sequenceName = "board_add_sequence", allocationSize = 1) // 시퀀스 트리거 같은 녀석
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="board_add_sequence")
	@Column(name = "board_id")
	private Long boardID;
	@Column(name = "title")
	private String title;
	@Column(name = "content")
	private String content;
	@Column(name = "author")
	private String author;
}
