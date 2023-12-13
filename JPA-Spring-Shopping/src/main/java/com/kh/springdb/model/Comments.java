package com.kh.springdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comments_seq")
	@SequenceGenerator(name = "comments_seq", sequenceName = "comments_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private String content;
	// 댓글에 좋아요를 누르고 싶다면 댓글 객체로 와서 추천에 관련된 필드를 설정해주면 됨.
}