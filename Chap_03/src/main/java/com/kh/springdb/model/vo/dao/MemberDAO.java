package com.kh.springdb.model.vo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.vo.MemberVO;

public interface MemberDAO extends JpaRepository<MemberVO, Long>{
	// 전체 조회에 관련된 메서드
	
	// DB에 삽입하는 메서드
	void insertMember(MemberVO member);
	// 수정에 관련된 메서드
	
	// 삭제에 관련된 메서드
}

// DAO와 Repository 적기
/*
 DAO와 Repository
 
 DAO(Data Access Object) : 
 	DB와 상호작용하는 것을 캡슐화하여 데이터와 자바에서 실행하는 코드와 분리하기 위해 사용됨.
 	주로 DB를 연결하거나, 쿼리를 실행하거나, 트랜잭션을 관리하는 것과 같은 작업을 진행.
 	
 Repository : 
 	스프링에서 주로 사용되며 데이터를 엑세세를 하기 위한 기능은 Bean 을 통해 제공
 	스프링에 제공하는 기능을 활용해서 데이터 엑세스를 편리하게 처리할 수 있음.
 	주로 인터페이스를 통해 사용되며, 사용자가 객체화가 아닌 추상(바로 직접적으로 쓰는게 아니라 살짝 돌려서 쓰는 것)화된 DB에 접근할 수 있음. 
 
 Bean : 
 	스프링 프레임워크에서 스프링에 의해 객체가 생성되고 관리되는 것을 말함. (ex ~ = new ~(); 이런 것들을 bean이 해결해줌.)
 */
