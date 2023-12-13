package com.kh.springdb.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.UserRole;
import com.kh.springdb.model.Users;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService{

	/*
	 * DB나 외부에서 로그인하여 인증을 하기 위해서는 인증처리를 해야 함. 
	 * 
	 * Security 안에는 UserDetailService가 있음
	 * UserDetailService :  사용자 정보를 인증하는 역할
	 */
		private final UserRepository userRepository;
		// 유저에 대한 정보를 로그인할 때 userDetails를 사용하여 로그인할 수 있는 유저가 있는지 확인.
		// 사용자명을 기준으로 사용자 정보를 가져오기
		
		@Override
		public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
			Optional<Users> _siteUser = userRepository.findByUserName(userName);
			if(_siteUser.isEmpty()) {
				throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
			}
			Users users = _siteUser.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			// 만약에 admin user로 로그인된다면 로그인 분류를 role에 따라 추가로 작성
			if("admin".equals(userName)) {
				authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
			} else {
				authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
			}
			
			return new User(users.getUserName(), users.getPassword(), authorities);
		}
}
	/*
	UserDetails : 스프링 시큐리티가 사용자의 인증과 권한 부여를 처리하는 데 필요한 정보를 제공. 인터페이스로 다양한 종류의 메서드가 있음.
		>메서드들
		getAuthorities() : 사용자가 가지고 있는 권한 목록을 반환하며 권한은 정의된 권한에 따라 달라짐. 권한은 개발자가 설정.(권한은 GrantedAuthority를 가지고 설정해줌)
		getPassword() : 사용자의 비밀번호를 반환하고 DB에서 암호화 처리된 형태로 저장되어 있음.
		getUserName() : 사용자명을 반환
		isEnables() : 계정이 활성화 돼있는지 여부를 Boolean 값으로 나타냄.
	*/
