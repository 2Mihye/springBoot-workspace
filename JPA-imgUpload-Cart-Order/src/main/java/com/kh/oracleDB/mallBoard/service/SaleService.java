package com.kh.oracleDB.mallBoard.service;

import org.springframework.stereotype.Service;

import com.kh.oracleDB.mallBoard.model.vo.Sale;
import com.kh.oracleDB.mallBoard.model.vo.User;
import com.kh.oracleDB.mallBoard.repository.ItemRepository;
import com.kh.oracleDB.mallBoard.repository.SaleItemRepository;
import com.kh.oracleDB.mallBoard.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final SaleRepository saleRepository;
	private final SaleItemRepository saleItemRepository;
	private final ItemRepository itemRepository;
	
	// 회원가입 하면 판매자 당 판매내역 하나 생성
	public void createSale(User user) {
		Sale sale = Sale.createSale(user);
		saleRepository.save(sale);
	}
}
