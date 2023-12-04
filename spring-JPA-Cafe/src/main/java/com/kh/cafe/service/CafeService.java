package com.kh.cafe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.cafe.repository.CafeRepository;
import com.kh.cafe.vo.Cafe;

@Service
public class CafeService {
	private final CafeRepository cafeRepository;
	
	@Autowired
	public CafeService(CafeRepository cafeRepository) {
		this.cafeRepository = cafeRepository;
	}
	
	public List<Cafe> getAllCafes(){ // 카페 전체 조회
		return cafeRepository.findAll();
	}
	
	public Optional<Cafe> getCafeByID(Long cafeID){ // 카페 하나 조회
		return cafeRepository.findById(cafeID);
	}
	
	public Cafe saveCafe(Cafe cafe) { // 카페 추가하기
		return cafeRepository.save(cafe);
	}
	
	public void deleteCafeByID(Long cafeID) { // 카페 삭제하기
		cafeRepository.deleteById(cafeID);
	}
	
	public void deleteAllCafes() { // 카페 모두 삭제하기
		cafeRepository.deleteAll();
	}
	
	
	public List<Cafe> findCafes(String keyword){ // 카페 검색하기
		return cafeRepository.findByCafeNameContaining(keyword);
	}
	
	public int countCafesByLocation(String location) { // 지역 카운터
		return cafeRepository.countByLocation(location);
	}
	
	public boolean existsCafeByName(String name) { // 카페 존재여부
		return cafeRepository.existsByName(name);
	}
}
