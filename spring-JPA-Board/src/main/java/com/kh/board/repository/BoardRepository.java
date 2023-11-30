package com.kh.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.board.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	// query를 적지 않는 이유는 이미 findAll 또는 findById 같은 것들이 내장되어있기 떄문
	// 게시판에서 제목에 특정 키워드가 포함된 게시물을 검색하게 하고 싶을 때
	@Query("SELECT b FROM Board b WHERE b.title Like %:keyword%")
	List<Board> findTitle(@Param("keyword") String keyword);
}

/*
 @Query : JPA에서 제공하는 CRUD 쿼리 이외에 추가적으로 필요한 쿼리가 있을 경우 직접 생성하여 쿼리를 정의할 때 사용하는 어노테이션으로 인터페이스 메서드에 직접 쿼리를 작성할 수 있음.
 		  해당 어노테이션을 통해 더 복잡한 검색이나 조인 등의 작업을 수행할 수 있다.
 		  
 		  %:keyword% : 파라미터로 받아온 키워드를 나타냄
 		  % : 어떤 문자열이라도 매칭이 될 수 있도록 도와주는 역할
 		  List<Board> : 검색 결과를 리스트 형태로 반환할 수 있도록 해줌
 		  @Param("keyword") : keyword에 해당하는 값을 메서드의 파라미터로 받아오기 위해 @Param이라는 어노테이션 사용
 		  					  메서드에서 매개변수로 전달된 keyword의 값을 쿼리 내에 :keyword에 매핑시킴
 		  					  
 		  					  
	@Query("SELECT * FROM board WHERE title Like %:keyword%")
	List<Board> findTitle(@Param("keyword") String keyword);
	
	@Query("SELECT b FROM board b WHERE b.title Like %:keyword%") // board b에서 b를 붙이는 것과 안 붙이는 것은 엔티티에서 별칭을 지정해서 사용하는 방식 차이
	List<Board> findTitle(@Param("keyword") String keyword); //
	
	
	JPQL{Java Persistence Query Language) : Java 객체를 대상으로 하는 쿼리로 JPA(Java Persistence API) 사용
											엔티티 객체와 필드에 대한 쿼리를 정의하는 데 사용
											JPQL 엔티티와 필드에 대한 쿼리를 작성할 떄 SQL과는 조금 다른 문법을 사용
 
 */
