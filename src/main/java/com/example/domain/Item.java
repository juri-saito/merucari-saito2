package com.example.domain;

/**
 * 商品情報を表すドメインクラス
 * @author juri.saito
 *
 */
public class Item {

	/** 商品ID */
	private Integer id;
	
	/** 商品名 */
	private String name;
	
	/** 商品状態 */
	private Integer condition;
	
	/** 商品カテゴリ */
	private Integer categoryId;
	
	/** カテゴリ名 */
	private String categoryName;

	/** ブランド */
	private String brand;
	
	/** 価格 */
	private double price;
	
	/** 発送 */
	private Integer shipping;
	
	/** 商品説明 */
	private String description;
	

	public Item() {
	}

	public Item(Integer id, String name, Integer condition, Integer categoryId, String categoryName, String brand,
			double price, Integer shipping, String description) {
		super();
		this.id = id;
		this.name = name;
		this.condition = condition;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", condition=" + condition + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", brand=" + brand + ", price=" + price + ", shipping=" + shipping
				+ ", description=" + description + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
		
}
