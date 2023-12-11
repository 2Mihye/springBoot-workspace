package com.kh.springdb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter // 안에는 Validation이 들어갈 수 없다.
@Setter
//@Entity
public class UserCheckForm { // ID 나 비밀번호가 적혀있지 않거나, 최대값 최소값을 설정해주기 위해 form을 체크해주는 클래스
	// 무결성 제약 조건에 어긋나는지 확인하는 form
	
	
	// 최대값 최소값을 설정해주고 빈 값이라면 적용되지 않게 할 빈 값 관련 문구 설정
	//@Id
	@Size(min = 2 , max = 8) // 최소값 = 2 , 최대값 8로 설정
	@NotEmpty(message = "사용자 ID는 필수로 입력해야 합니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수로 입력해야합니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호를 한 번 더 필수로 입력해야합니다.")
	private String password2;
	
	@NotEmpty(message = "이메일은 필수로 입력해야합니다.")
	@Email
	private String email;
	

}
