package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

/**
 * カテゴリ情報を操作するリポジトリ
 * @author juri.saito
 *
 */
@Repository
public class CategoryRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setParent(rs.getInt("parent"));
		category.setName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));
		return category;
	};

	/**
	 * カテゴリ情報を検索
	 * @param id　カテゴリID
	 * @return　カテゴリ情報
	 */
	public Category findById(int id) {
		String sql= "";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Category category = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
		return category;
	}
	
	/**
	 * 大カテゴリのリストを取得
	 * @return　大カテゴリのリスト
	 */
	public List<Category> findParent(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT id, name, parent, name_all FROM category WHERE parent IS null AND name_all IS null ORDER BY name;");
		List<Category> parentList = template.query(sql.toString(), CATEGORY_ROW_MAPPER);
		return parentList;
	}
	/**
	 * 大カテゴリに応じて中カテゴリのリストを取得
	 * @return　中カテゴリのリスト
	 */
	public List<Category> findChildByParent(String parentName){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, parent, name_all FROM (");
		sql.append("SELECT child.id, child.name, child.parent, child.name_all, parent.name AS parent_name FROM ");
		sql.append("(SELECT DISTINCT id, name, parent, name_all FROM category c1 WHERE parent IS NOT null AND name_all IS null ) AS child "); 
		sql.append("LEFT OUTER JOIN ");
		sql.append("category parent ");
		sql.append("ON child.parent = parent.id ");
		sql.append("ORDER BY child.name ");
		sql.append(") AS child_category WHERE child_category.parent_name = :parentName ");
		sql.append("ORDER BY name;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentName", parentName);
		List<Category> childList = template.query(sql.toString(), param, CATEGORY_ROW_MAPPER);
		return childList;
	}
	/**
	 * 大カテゴリ・中カテゴリに応じて小カテゴリのリストを取得
	 * @return　小カテゴリのリスト
	 */
	public List<Category> findGrandChildByChild(String parentName, String childName){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, parent, name_all ");
		sql.append("FROM category "); 
		sql.append("WHERE name_all LIKE ");
		sql.append(":parentAndChildName ");
		sql.append("ORDER BY name;"); 
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentAndChildName", parentName + "/" + childName + "%");
		List<Category> grandChildList = template.query(sql.toString(), param, CATEGORY_ROW_MAPPER);
		return grandChildList;
	}
}
