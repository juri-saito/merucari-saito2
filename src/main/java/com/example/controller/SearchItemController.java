package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.SearchItemForm;
import com.example.service.PullDownService;
import com.example.service.SearchItemService;

/**
 * 商品検索関連機能の処理の制御を行うコントローラ
 * @author juri.saito
 *
 */
@Controller
@RequestMapping("/search")
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;
	
	@Autowired
	private PullDownService pullDownService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 選択カテゴリに該当する商品一覧画面を表示
	 * @param 出力したいページ番号
	 * @return 選択カテゴリに該当する商品一覧画面
	 */
	@PostMapping("")
	public String searchItemsPost(Integer page, SearchItemForm form, Model model) {

		
		//①ページング機能追加
		session.setAttribute("searchItemForm", form);
		
		if(page == null) {
			//ページ番号の指定が無い場合は1ページ目を表示させる
			page = 1;
		}
		//TODO 検索機能
		
		//表示させたいページ番号を渡し、1ページに表示させる商品リストを取得
		Page<Item> itemPage = searchItemService.findByCategory(page, (SearchItemForm)session.getAttribute("searchItemForm"));
		
		model.addAttribute("itemPage", itemPage);
		
		//表示させたいページのページ番号をスコープに格納
				//TODO list.htmlに1ページ目と最終ページのときはprevボタンとnextボタンが消えるようにjquery書く
				int currentNumber = itemPage.getNumber();
				model.addAttribute("currentNumber", currentNumber);
				
				//表示させたいページの前のページ番号をスコープに格納
				model.addAttribute("prevPagePath", "/search?page=" + (currentNumber-1));
				//表示させたいページの次のページ番号をスコープに格納
				model.addAttribute("nextPagePath", "/search?page=" + (currentNumber+1));
				
		
		//総ページ数をスコープに格納
		int totalPages = itemPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);

		//TODO ②オートコンプリート
		
		//③大カテゴリのリストをスコープに格納
		List<Category> parentList = pullDownService.findParent();
		model.addAttribute("parentList", parentList);
		
		
		//④商品一覧画面出力
		return "list";
	}

	@GetMapping("")
	public String searchItemGet(Integer page, Model model) {
		
		model.addAttribute("indexForm", new SearchItemForm());
		
		//表示させたいページ番号を渡し、1ページに表示させる商品リストを取得
		Page<Item> itemPage = searchItemService.findByCategory(page, (SearchItemForm)session.getAttribute("searchItemForm"));
		
		model.addAttribute("itemPage", itemPage);
		
		//表示させたいページのページ番号をスコープに格納
				//TODO list.htmlに1ページ目と最終ページのときはprevボタンとnextボタンが消えるようにjquery書く
				int currentNumber = itemPage.getNumber();
				model.addAttribute("currentNumber", currentNumber);
				
				//表示させたいページの前のページ番号をスコープに格納
				model.addAttribute("prevPagePath", "/search?page=" + (currentNumber-1));
				//表示させたいページの次のページ番号をスコープに格納
				model.addAttribute("nextPagePath", "/search?page=" + (currentNumber+1));
				
		
		//総ページ数をスコープに格納
		int totalPages = itemPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);

		//TODO ②オートコンプリート
		
		//③大カテゴリのリストをスコープに格納
		List<Category> parentList = pullDownService.findParent();
		model.addAttribute("parentList", parentList);
		
		
		//④商品一覧画面出力
		return "list";
	}

}
