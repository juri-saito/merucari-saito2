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
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes(value = { "searchItemForm" }) 
@RequestMapping("/search")
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;
	
	@Autowired
	private PullDownService pullDownService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 検索条件に該当する商品一覧画面を表示
	 * @param 出力したいページ番号
	 * @return 検索条件に該当する商品一覧画面
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
	
		//TODO ③中カテゴリのリストをスコープに格納(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
		List<Category> childList = pullDownService.findChildByParent(form.getParentCategory());
		model.addAttribute("childList", childList);
		
		//TODO ③小カテゴリのリストをスコープに格納(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
		List<Category> grandChildList = pullDownService.findGrandChildByChild(form.getParentCategory(), form.getChildCategory());
		model.addAttribute("grandChildList", grandChildList);
		
		
		//④商品一覧画面出力
		return "list";
	}

	/**
	 * 検索後、前後ページを表示
	 * @param 出力したいページ番号
	 * @return 検索条件に該当する商品一覧画面
	 */
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
		
		//TODO ③中カテゴリのリストをスコープに格納(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
		List<Category> childList = pullDownService.findChild();
		model.addAttribute("childList", childList);
		
		//TODO ③小カテゴリのリストをスコープに格納(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
		List<Category> grandChildList = pullDownService.findGrandChild();
		model.addAttribute("grandChildList", grandChildList);
		
		
		//④商品一覧画面出力
		return "list";
	}
	
	
	
	/**
	 * ブランド名押下時、該当ブランド商品一覧画面を表示
	 * @param 出力したいページ番号
	 * @return 該当ブランド商品一覧画面
	 */
	@GetMapping("/brand")
	public String searchItemByBrand(String brand, Integer page, Model model) {
		
		SearchItemForm form = (SearchItemForm)session.getAttribute("searchItemForm");
		form.setSearchName("");
		form.setParentCategory("");
		form.setChildCategory("");
		form.setGrandChildCategory("");
		form.setSearchBrand(brand);
		
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
	
		//TODO ③中カテゴリのリストをスコープに格納(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
		List<Category> childList = pullDownService.findChildByParent(form.getParentCategory());
		model.addAttribute("childList", childList);
		
		//TODO ③小カテゴリのリストをスコープに格納(nameが同じで親カテゴリが異なるものの扱いについて再検討すること)
		List<Category> grandChildList = pullDownService.findGrandChildByChild(form.getParentCategory(), form.getChildCategory());
		model.addAttribute("grandChildList", grandChildList);
		
		
		//④商品一覧画面出力
		return "list";
	}
}
