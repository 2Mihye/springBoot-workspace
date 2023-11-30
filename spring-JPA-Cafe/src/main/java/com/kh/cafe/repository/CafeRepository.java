package com.kh.cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.cafe.vo.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long>{
	// 특정 문자열을 포함한 엔터티를 검색하는 데 사용하는 메서드 findByNameContaining(String keyword);
	//예시)
	List<Cafe>findByCafeNameContaining(String keyword);
	
	
	
	
	//@Query("SELECT c FROM Cafe c WHERE c.name LIKE %:keyword%")
	// 1. 만약 보여줄 것이 많다면 List로 담아서 한 번에 보여주기
	//List<Cafe> findCafe(@Param("keyword") String keyword);
	// 2. 보여줄 것이 하나라면 Cafe 객체 하나만 호출할 것
}

/*
 Query Creation : Spring Data JPA에서 제공하는 기능
  				  메서드에 규칙이 존재하고, 규칙에 따라서 메서드를 생성해주는 기능
  				  메서드 이름으로 DB 쿼리를 생성할 수 있다.
  				  
 List<Cafe>findByCafeNameContaining(String keyword) // Containing : 해당하는 변수명이 특정 변수에 대한 검색을 Like로 진행할 수 있게 도와줌.(= %~~%)
 		   countBy
 		    등등
 ->JPA 규칙을 지정하여 이 규칙만 지켜주면 JPA가 알아서 쿼리를 만들어주는 것
 
 
 	findBy+찾고싶은변수명(칼럼명) : 검색하고 싶을 때
	 	예를들어 Cafe라는 Class에 작성해놓은 
	 		private Long cafeID;
	 		private String cafeName;
	 		private String location;
	 		private String openingHours;
	 	에서 지역을 검색하고 싶다면
	 	findByLocation(String location) => SELECT * FROM cafe WHERE location = ? 으로 쿼리 자동생성
	 	운영시간을 검색하고 싶다면
	 	findByOpeningHours(String openingHours) => SELECT * FROM cafe WHERE openinghours = ?
	 
	countBy+세고싶은 클래스에 적어준 변수명 : 총 갯수를 계산하고 싶을 때
		countByLocation(String location) => SELECT COUNT(*) FROM cafe WHERE location = ?
		
	existsBy+존재확인원하는 클래스에 적어준 변수명 : 존재 여부를 확인하고 싶을 때
		existsByLocation(String location) => SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM cafe WHERE location = ?
		
	deleteBy+삭제 원하는 클래스에 적어준 변수명 : 삭제하고 싶을 때
		deleteByLocation(String location) => DELETE FROM cafe WHERE location = ?
 */
