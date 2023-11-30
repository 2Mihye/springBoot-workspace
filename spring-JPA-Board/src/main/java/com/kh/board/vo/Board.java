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

/*
 @Entity : 클래스를 선언해주는 어노테이션
 @Id : 기본키를 설정해주도록 하는 어노테이션
 @Column : 필드와 컬럼 매핑.
 @Lob : Blob, Clob 타입을 매핑
 @CreationTimestamp : insert 시 시간을 자동으로 저장
 @UpdateTimestamp : update시 시간을 자동으로 저장
 @Temporal : 날짜 타입을 매핑.
 @CreateDate : 엔티티가 생성되어 저장될 때 시간을 저장
 
 @Column : 테이블 안에 컬럼을 생성하거나 존재하는 테이블에 컬럼값을 찾아 매핑하는 역할
 		option - name : DB에서 존재하는 컬럼명과 java 클래스에서 존재하는 필드 이름이 일치하지 않을 경우 둘의 java와 DB 컬럼명이 일치할 수 있도록 매핑해주는 역할
 				 unique : 유니크 제약 조건 설정
 				 insertable : insert 가능 여부
 				 updateable : update 가능 여부
 				 length : String 타입의 문자 길이 제약조건 설정
 				 columnDefinition : DB 컬럼 정보를 직접 기술. ex) @Column(columnDefinition = "varchar(10) not null")
 				 precision : 큰 값에서 사용 가능. 소수점을 포함한 전체 자리수
 				 scale(DDL) : 소수점 자리수 Double과 float 타입에는 적용되지 않음.

@GeneratedValue(strategy = GenerationType.)
	GenerationType.AUTO(default) : JPA이 자동으로 알아서 생성 전략을 결정
				  .SEQUENCE : DB 시퀀스를 이용하여 기본키를 생성하고 간혹 @SequenceGenerator을 사용하여 시퀀스를 등록할 필요가 있음.
				  .TABLE : 키 생성용 테이블 사용. @TableGenerator 필요.
				  .IDENTITY : 기본키 생성을 DB에 넘겨줌. ex) mySQL DB는 AUTO_INCREMENT를 사용하여 기본키 생성
 */
