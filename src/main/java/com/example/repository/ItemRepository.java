package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;

import com.example.domain.Item;
import com.example.form.SearchItemForm;

/**
 * 商品情報の操作に用いるリポジトリ
 * @author juri.saito
 *
 */
@Controller
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 商品情報のローマッパーを定義
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setCondition(rs.getInt("condition"));
		item.setCategoryId(rs.getInt("category"));
		item.setCategoryName(rs.getString("name_all"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getDouble("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));
		return item;
	};
	
	/**
	 * 商品情報を全件検索
	 * @return 全商品リスト
	 */
	public List<Item> findAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id, i.name, i.condition, i.category, c.name_all, i.brand, i.price, i.shipping, i.description FROM items i LEFT OUTER JOIN category c ON i.category = c.id ORDER BY i.id;");
		List<Item> itemList = template.query(sql.toString(), ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 全商品点数を取得
	 * @return 全商品点数
	 */
	public Integer findAllItemCount(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) FROM items;");
		SqlParameterSource param = null;
		Integer count = template.queryForObject(sql.toString() ,param, Integer.class); //クラス名のオブジェクトを生成して結果を詰めて返す
		return count;
	}
	
	
	/**
	 * 商品情報を30件検索
	 * @param どの商品から表示させるかというカウント値（１つのページの先頭の商品番号）
	 * @return 30件分の商品リスト
	 */
	public List<Item> findAPageItems(int startItemCount){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id, i.name, i.condition, i.category, c.name_all, i.brand, i.price, i.shipping, i.description FROM items i LEFT OUTER JOIN category c ON i.category = c.id ORDER BY i.id LIMIT 30 OFFSET :startItemCount;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("startItemCount", startItemCount);
		List<Item> itemList = template.query(sql.toString(), param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 選択されたカテゴリに応じた商品情報を30件検索
	 * @param startItemCount　どの商品から表示させるかというカウント値（１つのページの先頭の商品番号）
	 * @return 30件分の商品リスト
	 */
	public List<Item> findByCategory(int startItemCount,  SearchItemForm form){
		StringBuilder sql = new StringBuilder();
		List<Item> itemList = new ArrayList<Item>();
		sql.append("SELECT i.id, i.name, i.condition, i.category, c.name_all, i.brand, i.price, i.shipping, i.description FROM items i LEFT OUTER JOIN category c ON i.category = c.id WHERE SPLIT_PART(name_all ,'/', 1) Like :parentName AND SPLIT_PART(name_all ,'/', 2) Like :childName AND SPLIT_PART(name_all ,'/', 3) Like :grandChildName ORDER BY i.id LIMIT 30 OFFSET :startItemCount;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("startItemCount", startItemCount).addValue("parentName", "%" + form.getParentCategory() + "%").addValue("childName", "%" + form.getChildCategory() + "%").addValue("grandChildName", "%" + form.getGrandChildCategory() + "%");			
		
		itemList = template.query(sql.toString(), param, ITEM_ROW_MAPPER);
		
		return itemList;
	}
	
	
}
