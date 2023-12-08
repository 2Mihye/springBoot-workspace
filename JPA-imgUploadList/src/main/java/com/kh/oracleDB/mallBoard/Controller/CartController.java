package com.kh.oracleDB.mallBoard.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.oracleDB.mallBoard.model.Cart;
import com.kh.oracleDB.mallBoard.model.CartItem;
import com.kh.oracleDB.mallBoard.model.Item;
import com.kh.oracleDB.mallBoard.service.CartService;
import com.kh.oracleDB.mallBoard.service.ItemService;

@Controller
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;
	private final ItemService itemService;
	
	// 아래 생성자는 @Autowired와 비슷한 녀석임.
	public CartController(CartService cartService, ItemService itemService) {
		this.cartService = cartService;
		this.itemService = itemService;
	}
	
	// 장바구니 목록을 보여주기 위한 GetMapping
	@GetMapping
	public String viewCart(Model model) {
		Cart cart = cartService.getCartById(1L);
		model.addAttribute("cart", cart);
		return "cartView";
	}
	
	// 주소를 접속하기 위해서 GetMapping을 작성
	@GetMapping("/add/{itemId}")
	public String addToCart(@PathVariable("itemId") int itemId, Model model) {
		// 장바구니에 상품 추가
		Item newItem = itemService.getItemById(itemId);
		// @PathVariable Long itemId : 만약 파라미터 값이 Long일 경우 longValue() 작성
		// Service에서 .intValue() Integer이라고 쓴 객체를 int로 변환하는 메서드
		//Item newItem = itemService.getItemById(itemId.intValue());
		
		//cartService.addCart (장바구니이기 때문에 누가 장바구니를 소유하고 있는지에 대한 값을 지정, newItem, 1);
		
		cartService.addCart(1L, newItem, 1); // 임의의 값을 지정해줄 때 1L이라는 표현을 쓰기도 함. 타입이 Long이기때문에 L을 사용
		return "redirect:/cart";
	}
	
	@PostMapping("/add")
	public String addToCartItem(@RequestParam("itemId") Long itemId, Model model) {
		Item newItem = itemService.getItemById(itemId.intValue());
					// 1L :  유저 ID값, newItem : 새로운 아이템 추가, 1 : 카트에 추가할 아이템 수량
		cartService.addCart(1L, newItem, 1);
		return "redirect:/cart";
	}
	
	// 결제 완료 후 장바구니 삭제하기 위한 메서드 추가
	@PostMapping("/checkout")
	public String checkout(RedirectAttributes redirectAttribute) {
		Long cartId = 1L; // User을 연결하고 나면 1L 대신에 로그인한 유저 값이 들어가야 함.
		try {
			cartService.checkout(cartId);
			redirectAttribute.addFlashAttribute("checkoutStatus", "success");
		} catch(Exception e) {
			redirectAttribute.addFlashAttribute("checkoutStatus", "empty");
		}
		return "redirect:/cart";
	}
}

/*  
   *Integer과 int의 차이
	Integer : Wrapper 클래스로 객체로 감싸져 있기 떄문에 null 값을 가질 수 있음.
	int : Java에서 기본 데이터 타입으로 정수를 나타내는 값. null 값을 가질 수 없음.
	
	
	*RedirectAttributes : redirect할 때 속성을 전달하는 데 사용.
	*addFlashAttribute  : 데이터를 추가할 때 redirect 후 한 번만 사용 가능. 사용 후에는 속성이 자동으로 삭제됨. (1회성)
						 redirect해서 돌아가고자 하는 페이지로 이동할 때 속성이 존재하고, 돌아간 페이지에서 속성을 사용할 수 있음.
*/
