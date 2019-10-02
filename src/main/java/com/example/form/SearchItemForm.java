package com.example.form;

/**
 * 商品検索情報を受け取るフォームクラス
 * @author juri.saito
 *
 */
public class SearchItemForm {
	
	/** 商品名検索ワード */
	private String searchName;

	/** 大カテゴリ */
	private String parentCategory;
	
	/** 中カテゴリ */
	private String childCategory;
	
	/** 小カテゴリ */
	private String grandChildCategory;
	
	/** ブランド名検索ワード */
	private String searchBrand;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	public String getGrandChildCategory() {
		return grandChildCategory;
	}

	public void setGrandChildCategory(String grandChildCategory) {
		this.grandChildCategory = grandChildCategory;
	}

	public String getSearchBrand() {
		return searchBrand;
	}

	public void setSearchBrand(String searchBrand) {
		this.searchBrand = searchBrand;
	}
	

}
