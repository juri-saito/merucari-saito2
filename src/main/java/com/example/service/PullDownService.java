package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

/**
 * 商品検索関連の業務処理を行うサービス
 * @author juri.saito
 *
 */
@Service
@Transactional
public class PullDownService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * プルダウン表示のために大カテゴリのリストを取得
	 * @return　大カテゴリのリスト
	 */
	public List<Category> findParent(){
		List<Category> parentList = categoryRepository.findParent();
		return parentList;
	}
	
	/**
	 * TODO プルダウン表示のために中カテゴリのリストを取得(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
	 * @return　中カテゴリのリスト
	 */
	public List<Category> findChild(){
		List<Category> parentList = categoryRepository.findChild();
		return parentList;
	}
	
	/**
	 * TODO プルダウン表示のために中カテゴリのリストを取得(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
	 * @return　中カテゴリのリスト
	 */
	public List<Category> findGrandChild(){
		List<Category> grandChildList = categoryRepository.findGrandChild();
		return grandChildList;
	}

	
	/**
	 * 大カテゴリに応じて中カテゴリのリストを取得
	 * @return　中カテゴリのリスト
	 */
	public List<Category> findChildByParent(String parentName){
		List<Category> childList = categoryRepository.findChildByParent(parentName);
		return childList;
	}

	/**
	 * 大中カテゴリに応じて小カテゴリのリストを取得
	 * @return　小カテゴリのリスト
	 */
	public List<Category> findGrandChildByChild(String parentName, String childName){
		List<Category> GrandChildList = categoryRepository.findGrandChildByChild(parentName,childName);
		return GrandChildList;
	}
	

}
