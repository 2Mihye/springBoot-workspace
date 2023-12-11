package com.kh.springdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링에서 환경설정 파일임을 나타내는 어노테이션이자 시큐리티 설정을 의미하는 어노테이션
@EnableWebSecurity // 모든 url이 스프링 시큐리티의 제어를 받도록 만든 어노테이션
public class SecurityConfig { // 전체 허용
	@Bean // 객체의 생성, 관리, 주입(새로 넣어줌)을 담당
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests//.authorizeRequests는 구버전
        			.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // '/**'는 모든 폴더의 권한 허용
			
			// 로그인을 하고난 후 로그인 세션을 유지하기 위한 설정
        	.formLogin((formLogin) -> formLogin
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/")) // 맨 위 부터 하위 공간까지 한번에 로그인 세션을 주겠다는 의미
			
			// 로그아웃 하고난 후 로그인 세션을 끝내기 위한 설정
			.logout((logout) -> logout // 마찬가지로 로그아웃도 맨 위부터 하위 공간까지 로그아웃을 한 번에 끝내겠다는 의미
					 .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	                    .logoutSuccessUrl("/")
	                    .invalidateHttpSession(true))
			;
		
		return http.build();
	}
	@Bean
	AuthenticationManager authenticationManage(AuthenticationConfiguration authenticationConfiguration) throws Exception{ // 사용자 인증정보를 담아줌.
		return authenticationConfiguration.getAuthenticationManager();
	}

	// 비밀번호 변경시 어떤 식으로 암호 처리를 할 지 설정
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } // BCryptPasswordEncoder : BCryptPasswordEncoder 형식으로 저장
}
