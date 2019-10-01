package com.example.domain;

/**
 * カテゴリ情報を表すドメインクラス
 * @author juri.saito
 *
 */
public class Category {

	/** カテゴリID */
	private Integer id;
	
	/** 親ID */
	private Integer parent;
	
	/** カテゴリ名 */
	private String name;
	
	/** カテゴリ名全文 */
	private String nameAll;
	
	

	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", nameAll=" + nameAll + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}
	
	
}
