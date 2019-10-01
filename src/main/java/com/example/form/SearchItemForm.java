package com.example.form;

/**
 * 商品検索情報を受け取るフォームクラス
 * @author juri.saito
 *
 */
public class SearchItemForm {

	/** 大カテゴリ */
	private String parentCategory;
	
	/** 中カテゴリ */
	private String childCategory;
	
	/** 小カテゴリ */
	private String grandChildCategory;
	
	
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
	
}
