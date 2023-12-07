package com.kh.oracleDB.mallBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracleDB.mallBoard.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
