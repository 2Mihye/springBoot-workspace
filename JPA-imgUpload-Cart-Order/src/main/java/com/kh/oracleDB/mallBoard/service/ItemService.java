package com.kh.oracleDB.mallBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.oracleDB.mallBoard.model.vo.CartItem;
import com.kh.oracleDB.mallBoard.model.vo.Item;
import com.kh.oracleDB.mallBoard.repository.ItemRepository;

import jakarta.transaction.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {
	
	private final ItemRepository itemRepository;
	private final CartService cartService;
	private final SaleService saleService;
	
	/*
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}*/
	
	
	// 상품을 추가하고 삭제하고 수정하는 기능
	public void addItem(Item item, MultipartFile photoFile) throws IllegalStateException, IOException {
		// 상품명과 저장될 파일명 경로 생성
		// 이미지 파일 정보에 대해서 추출
		String originalPhotoName = photoFile.getOriginalFilename(); // 업로드 된 이미지 파일의 원본 파일명을 가져옴.
		String photoName = "";
		
		String photoPath = System.getProperty("user.dir") + "src/main/resources/static/img"; // 업로드 된 이미지 파일 경로를 설정
			// System.getProperty() = 현재 코드가 작업하고 있는 폴더 위치
			// "user.dir" = 현재 작업하고 있는 사용자 폴더를 나타냄. C:/Users/user1/springBoot-workspace/JPA-ImgUpload-Cart-Order 과 위치가 동일함.
		// UUID uuid = UUID.randomUUID(); // 새로운 파일명을 생성해주기 위해 UUID 사용
		
		String saveFileName = "OnionShop_" + originalPhotoName; // saveFileName으로 만약에 판매자가 사진1을 올리면 내 폴더 안에는 OnionShop_사진1로 저장
		
		photoName = saveFileName; // 빈 값에다가 한번 더 재정의로 넣어줌
		
		// 만약 이름을 변경하여 저장하고 싶지 않다면, originalPhotoName으로 저장해도 됨. => 판매자 컴퓨터에 있는 이미지 이름으로 그대로 업로드 됨.
		File saveFile = new File(photoPath,photoName); // 파일을 저장하기 위해 우리가 작성해놓은 파일을 작성하기 위한 공간이 비어있는 File 객체를 가지고 옴.
							 // (파일을 저장할 경로, 파일명 )
		
		photoFile.transferTo(saveFile); // 업로드 된 이미지 파일을 지정된 경로에 저장하기 위해 설정
			   // transforTo : 서버에 특정 경로에 저장할 수 있도록 해주는 메서드
		item.setPhotoName(photoName);
		item.setPhotoPath("/img/" + photoName);
				
		itemRepository.save(item); // DB에 저장할 수 있도록 save
	}
	
	// 상품 읽기 find를 사용하여 개별 읽기
	public Item getItemById(Integer id) {
		return itemRepository.findById(id).get();
	}
	
	// findById를 사용하여 Id에 해당하는 값을 가져오고 get을 이용하여 Item 객체를 반환
	
	// 모든 상품 가져오기 List
	public List<Item> allItemList() {
		return itemRepository.findAll();
	}
	
	// 상품 리스트 페이지용 불러오기
	public Page<Item> allItemViewPage(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}
	
	// 상품 수정
	@Transactional
	public void itemModify(Item item, Integer id, MultipartFile photoFile) throws Exception {
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + photoFile.getOriginalFilename();
		File saveFile = new File(projectPath, fileName);
		photoFile.transferTo(saveFile);
		
		Item update = itemRepository.findItemById(id);
		update.setName(item.getName());
		update.setDescription(item.getDescription());
		update.setPrice(item.getPrice());
		update.setStock(item.getStock());
		update.setIsSoldOut(item.getIsSoldOut());
		update.setPhotoName(fileName);
		update.setPhotoPath("/img/" + fileName);
		itemRepository.save(update);
	}
	
	// 상품 삭제
	@Transactional
	public void itemDelete(Integer id) {
		// cartItem 중에 해당 id를 가진 item 찾기
		List<CartItem> items = cartService.findCartItemByItemId(id);
		for(CartItem item : items) {
			cartService.cartItemDelete(item.getId());
		}
		itemRepository.deleteById(id);
	}
	
	// 상품 검색
	public Page<Item> itemSearchList(String searchKeyword, Pageable pageable) {
		return itemRepository.findByNameContaining(searchKeyword, pageable);
	}
}
