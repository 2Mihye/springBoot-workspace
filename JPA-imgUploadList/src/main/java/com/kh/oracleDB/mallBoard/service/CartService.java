package com.kh.oracleDB.mallBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.oracleDB.mallBoard.model.Cart;
import com.kh.oracleDB.mallBoard.model.CartItem;
import com.kh.oracleDB.mallBoard.model.Item;
import com.kh.oracleDB.mallBoard.model.Orders;
import com.kh.oracleDB.mallBoard.repository.CartItemRepository;
import com.kh.oracleDB.mallBoard.repository.CartRepository;
import com.kh.oracleDB.mallBoard.repository.ItemRepository;
import com.kh.oracleDB.mallBoard.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {
  @Autowired
  private CartItemRepository carItemRepository;

  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CartRepository cartRepository;

  public List<CartItem> findCartItemByCartId(int cartId) {
      return carItemRepository.findCartItemByItemId(cartId);
  }

  public List<CartItem> findByItemId(int itemId) {
      return carItemRepository.findByItemId(itemId);
  }

  public Cart getCartById(Long cartId) {
      return cartRepository.findById(cartId).orElse(null);
  }

	@Transactional
	public void addCart(Long cartId, Item newItem, int amount) {
	    // 현재 담긴 장바구니가 없을 때 장바구니 생성해주는 코드
	    Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
	        Cart newCart = new Cart();
	        return cartRepository.save(newCart);
	    });

	    // 장바구니에 해당 아이템이 이미 담겨져 있는지 확인
	    CartItem cartItem = carItemRepository.findByCartIdAndItemId(cartId, newItem.getId());

	    if (cartItem == null) {
	        // 장바구니에 해당 아이템이 없으면 새로운 CartItem 생성
	        cartItem = new CartItem();
	        cartItem.setId(amount);
	        cartItem.setCart(cart);
	        cartItem.setItem(newItem);
	        cartItem.setCount(amount);
	    } else {
	        // 장바구니에 해당 아이템이 이미 담겨져 있으면 수량 증가
	        cartItem.addCount(amount);
	    }

	    // 생성 또는 업데이트된 CartItem을 저장
	    carItemRepository.save(cartItem);
 
	}
	
	// 결제하기
	@Transactional
	public void checkout(Long cartId) {
		// 주문할 아이템 정보를 찾기 위해 cart entity 정보를 가지고옴
		Cart cart = cartRepository.findById(cartId).orElse(null);
		// Cart : 어떤 유저가 장바구니에 물건을 담았는지 user 와 cart를 연결해주는 역할
		// CartItem : 장바구니에 어떤 아이템이 담겼는지 cart 와 item을 연결해주는 역할
		
		
		// 만약 cart가 null이 아닐 때
		if(cart != null) {
		 // Orders order =     Orders   +   cart(cart)=build();
			Orders order = Orders.builder().cart(cart).build(); // Order 객체를 가지고 온 것임.
			
			orderRepository.save(order); // 결제 이후 문제가 생길 것을 대비하여 DB 안에도 주문한 사람과 주문 날짜와 같은 주문 내역을 저장할 예졍.
			
			// delete와 clear
			cartRepository.deleteAll();
			cart.getCartItems().clear(); // 주문하고나면 장바구니를 비워야 하기 때문에 장바구니 지우는 코드 설정
			cartRepository.save(cart);
		}
	}
}


/*
@Service
@RequiredArgsConstructor
public class CartService {
	private final CartItemRepository cartItemRepository;
	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;
	
	public List<CartItem> findByCartItemByCartId(int cartId){
		return cartItemRepository.findCartItemByItemId(cartId);
	}
	
	// findByItemId
	public List<CartItem> findByItemId(int itemId){
		return cartItemRepository.findByItemId(itemId);
	}
	
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
	
	@Transactional
	public void addCart(Long cartId, Item newItem, int amount) {
		
		// 유저 정보 찾기
		
		
		// 현재 담긴 장바구니가 없을 경우 장바구니 생성해주는 코드
		Cart cart = cartRepository.findById(cartId).orElseGet(()-> {
			Cart newCart = new Cart();
			return cartRepository.save(newCart);
		});
		
		// Item item = itemRepository.findById(newItem).orElseThrow(() -> new Exception());
		CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cartId, newItem.getId());
		
		// 카트 아이템에서 Id 값이 없을 때 추가해주는 CartItem이기 때문에
		if(cartItem == null) {
			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setItem(newItem);
		} else {
	        // 장바구니에 해당 아이템이 이미 담겨져 있으면 수량 증가
	        cartItem.addCount(amount);
	    }
		
		carItemRepository.save(cartItem);
		
		// 재고 

		
		Item item = itemRepository.findItemById(newItem.getId()); // item의 id를 가지고 내가 담고자하는 아이템의 정보를 가지고 옴
		
		CartItem cartItem = cartItemRepository.findCartItemById(item.getId()); // 장바구니에 어떤 아이템이 담겨져있는가
		
		//만약 장바구니에 상품이 존재하지 않는다면 카트에 상품을 생성한 다음 상품 추가
		
		// 장바구니에 상품이 존재한다면 수량만 증가
		CartItem cartUpdate = cartItem;
		cartUpdate.setItem(item);
		cartUpdate.addCount(amount);
		cartUpdate.setCount(cartUpdate.getCount());
		cartItemRepository.save(cartUpdate);
		// return
		
		// 카트 상품 총 개수 증가를 카트 안에도 넣어줄 예정
		
	}

}*/
