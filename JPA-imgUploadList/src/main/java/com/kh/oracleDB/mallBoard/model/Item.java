package com.kh.oracleDB.mallBoard.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

/**************************************************** 1 *****************************************************/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Item {
	// id name text price count stock isSoldOut
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="item_seq")
	@SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
	private int id;
	
	private String name; // 물건이름
	private String text; // 물건 설명
	private int price; // 물건 가격
	private int count; // 판매 개수
	private int stock; // 재고
	private int isSoldOut; // 품절유무
	
	// 이미지 업로드를 위한 파일명, 이미지 경로, 상품 등록 날짜
	private String imgName;
	private String imgPath;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
	// DB에 값을 넣을 때 자동으로 생성된 날짜가 들어감
	@PrePersist
	public void createDate() { 
		this.createDate = LocalDate.now();
	}
	
	// 판매자가 누구인지, 장바구니에 어떤 아이템이 들어가 있는지는 아직 작성하지 않음!
	
	
}
