package com.kh.oracleDB.mallBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.oracleDB.mallBoard.model.Item;
import com.kh.oracleDB.mallBoard.repository.ItemRepository;

import lombok.RequiredArgsConstructor;


/**************************************************** 3 *****************************************************/

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	
	// 모든 상품 리스트 불러오기
	public List<Item> allItemView(){
		return itemRepository.findAll();
	}
	
	
	
	// 상품을 등록할 수 있는 메서드
	// 기존에는 상품명이나 글자로 이루어진 내용을 넣었지만 이미지를 넣어주기 위해 파일을 파라미터에 받겠다고 작성해줄 것 !
	public void saveItem(Item item, MultipartFile imgFile) throws IllegalStateException, IOException {
		String originName = imgFile.getOriginalFilename();
		String imgName = "";
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		
		//UUID uuid = UUID.randomUUID();
		
		//String savedFileName = uuid + originName; // 파일명 -> imgName
		// uuid 붙여줌으로 랜덤으로 숫자를 생성해서 파일명이 겹치지 않게 임의 숫자를 제공.
		
		File saveFile = new File(projectPath, originName);
		
		imgFile.transferTo(saveFile); // throw 작성
		
		item.setImgName(projectPath);
		item.setImgPath("/img/" + originName);
		
		itemRepository.save(item);
		
		/*
		 * MultipartFile : 인터페이스. 클라이언트로부터 전송된 파일 데이터를 처리하기 위한 메서드를 정의.
		 * 				   form 에서 multipart/form-data로 전송된 파일을 처리할 때 사용
		 * 
		 * 			File : java에서 제공하는 클래스. 로컬(개발자)컴퓨터에서 파일을 저장하거나 수정할 때 사용.
		 * 
		 * 	  transferTo : MultipartFile 인터페이스안에 있는 메서드. 업로드된 파일을 지정된 파일 경로로 저장하기 위해 사용. 안전하게 파일을 저장하도록 해줌.
		 */
	}
	
	// 상품 상세보기나 수정하기를 할 수 있는 메서드
	public Item getItemById(int id) {
		return itemRepository.findItemById(id);
	}
}
