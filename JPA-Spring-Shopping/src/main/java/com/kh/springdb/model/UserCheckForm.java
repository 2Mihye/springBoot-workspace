package com.kh.springdb.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UserCheckForm { // 사용자 ID나 비밀번호 이메일을 회원가입할 때 필수로 넣었는지 확인
	/* Size&NotEmpty&Email을 import 하기 위해선 pom.xml에 dependency 추가!
	 	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
			<version>2.4.2</version>
		</dependency>
		
		
		@NotNull  : 만약 넣어준 값이 null 값이라면 메세지가 나올 수 있도록 표기 -> Null 체크여부
		
		@NotEmpty : 메세지를 예외값으로 발생시킴. -> Empty 예외체크
	 */
	@NotNull(message = "가입자 선택은 필수입니다.")
	private UserRole isRole;
	
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 ID는 필수로 입력해야 합니다.")
	private String userName;
	
	@NotEmpty(message = "비밀번호는 필수로 입력해야 합니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인이 필요합니다.")
	private String password2;
	
	@NotEmpty(message = "이메일은 필수로 입력해야 합니다.")
	@Email
	private String email;
}
