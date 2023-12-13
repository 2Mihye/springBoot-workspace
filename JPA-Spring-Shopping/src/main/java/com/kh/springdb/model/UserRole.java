package com.kh.springdb.model;

import lombok.Getter;

/*
 변수 : 변할 수 있는 수
 상수 : 언제나 한결같은 수 (final)
 
 enum : final 상수 집합을 나타낼 때 사용하는 값
 */


@Getter
public enum UserRole { 
	// admin
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER"); // 나열해야 하는 상수들은 ,(콤마)를 사용하고 나열을 종료할 때는 최종적으로 ;(세미콜론)사용
	
	private String value; // 현재 유저가 ADMIN인지 USER인지 로그인하기 전까지 파악되지 않기 때문에 value 라는 값으로 추후에 로그인 할 경우 value에다 ADMIN 혹은 USER을 넣어준다.
	UserRole(String value){ // 유저가 어떤 유저인지 값을 받아오기 위해 value 추가
		this.value = value;
	}
}
