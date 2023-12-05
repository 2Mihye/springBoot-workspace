package com.kh.oracleDB.mallBoard.model.vo;

import java.time.LocalDate;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cart {
	// 구매자의 장바구니 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
	private int id;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user; // 구매자
    
    private int count; // 카트에 담긴 총 상품 개수
	
    /*
	@OneToOne() // fetch에 관련된 타입을 작성해줄 것
	@JoinColumn(name = "user_id")
	User user; // 구매자의 장바구니
	*/
	@OneToMany(mappedBy = "cart") // 장바구니 안에 상품들이 담길 예정이기 때문에 장바구니 1 : 상품들 Many
	private List<CartItem> cartItems = new ArrayList<>();
	
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist
    public void createDate(){
        this.createDate = LocalDate.now();
    }
    
    public static Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setCount(0);
        cart.setUser(user);
        return cart;
    }
}
