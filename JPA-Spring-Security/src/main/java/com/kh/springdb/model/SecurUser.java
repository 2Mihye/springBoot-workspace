package com.kh.springdb.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class SecurUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secq_user_seq")
	@SequenceGenerator(name = "secq_user_seq", sequenceName = "secq_user_seq", allocationSize = 1)
	private Long id;
	
	@Column(unique = true) // 중복 방지
	private String username;
	
	private String password;
	
	@Column(unique = true) // 중복 방지
	private String email;
	
}
