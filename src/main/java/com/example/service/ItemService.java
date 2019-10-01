package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報関連の業務処理を行うサービス
 * @author juri.saito
 *
 */
@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;


	/**
	 * 商品情報を全件検索
	 * ページング機能で使わなくなる予定
	 * @return 全商品リスト
	 */
	public List<Item> findAll(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	/**
	 * ページング用メソッド
	 * @param page　表示させたいページ番号
	 * @param size　1つのページに表示させる商品数
	 * @param itemList 絞り込み対象リスト
	 * @return 1ページに表示されるサイズ分の商品一覧情報
	 */
	public Page<Item> showListPaging(int page){
		//どの商品から表示させるかというカウント値（1つのページの先頭の商品）
		int startItemCount = (page-1) * 30; //1ページに30個の商品を表示する設定
		
		//表示させたいページの商品リスト
		List<Item> list = itemRepository.findAPageItems(startItemCount);
		
		//上記で作成した該当ページに表示させる商品一覧をページングできる形に変換して返す
		Page<Item> itemPage = new PageImpl<Item>(list, PageRequest.of(page, 30), itemRepository.findAllItemCount());
		
		return itemPage;
	}
}
