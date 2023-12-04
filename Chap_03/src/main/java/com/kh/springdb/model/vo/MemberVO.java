package com.kh.springdb.model.vo;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "members")
public class MemberVO {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
	@Id
	@Column(name = "user_no")
	private Long userNo;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_pw")
	private String userPw;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_addr")
	private String userAddr;
	@Column(name = "reg_date")
	private Date regDate; // Date를 import 할 때는 어지간해서는 util로 하는 것이 좋음 !
	
}
