package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Category;
import com.example.service.PullDownService;

/**
 * 商品検索関連機能の処理の制御を行うコントローラ
 * @author juri.saito
 *
 */
@Controller
@RequestMapping("/pulldown")
public class PullDownController {

	@Autowired
	private PullDownService pullDownService;
	
	/**
	 * 大カテゴリに入力された値に応じて中カテゴリのリストを変える
	 * @return　中カテゴリのリスト
	 */
	@RequestMapping(value="/child/{value}",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String changeChildPulldown(@PathVariable("value")String value){
		
		//受け取るデータはURLにくっつけるため必ずStringとなる
		String parentName = value;
		
		//大カテゴリ（parentName）に応じた小カテゴリを取得
		List<Category> childList = pullDownService.findChildByParent(parentName);
		
		StringBuilder s = new StringBuilder();
		String str = "";
		s.append("[");
		//文字列の中にダブルクォーテーションを入れるため、エスケープさせるのを忘れないこと
		for(int i=0; i<childList.size(); i++){
		        s.append("{\"");
		        s.append("itemValue");
		        s.append("\"");
		        s.append(":");
		        s.append("\"");
		        s.append(childList.get(i).getName());
		        s.append("\"");
		        s.append(",");
		        s.append("\"");
		        s.append("itemLabel");
		        s.append("\"");
		        s.append(":");
		        s.append("\"");
		        s.append(childList.get(i).getName());
		        s.append("\"}");
		        s.append(",");
		    }

		    //末尾のカンマを削除し、[{}]でくくった形になるようにしてStringに変換する
		    s.deleteCharAt(s.lastIndexOf(","));
		    s.append("]");
		    str = s.toString();
		    
		    return str;
	}
	
	/**
	 * 中カテゴリに入力された値に応じて小カテゴリのリストを変える
	 * @return　小カテゴリのリスト
	 */
	@RequestMapping(value="/grandChild/{value1}/{value2}",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String changeGrandChildPulldown(@PathVariable("value1")String value1, @PathVariable("value2")String value2){
		
		//受け取るデータはURLにくっつけるため必ずStringとなる
		String parentName = value1;
		String childName = value2;
		
		//大カテゴリ・中カテゴリに応じた小カテゴリを取得
		List<Category> grandChildList = pullDownService.findGrandChildByChild(parentName, childName);
		
		
		StringBuilder s = new StringBuilder();
		String str = "";
		s.append("[");
		//文字列の中にダブルクォーテーションを入れるため、エスケープさせるのを忘れないこと
		for(int i=0; i<grandChildList.size(); i++){
			s.append("{\"");
			s.append("itemValue");
			s.append("\"");
			s.append(":");
			s.append("\"");
			s.append(grandChildList.get(i).getName());
			s.append("\"");
			s.append(",");
			s.append("\"");
			s.append("itemLabel");
			s.append("\"");
			s.append(":");
			s.append("\"");
			s.append(grandChildList.get(i).getName());
			s.append("\"}");
			s.append(",");
		}
		
		//末尾のカンマを削除し、[{}]でくくった形になるようにしてStringに変換する
		s.deleteCharAt(s.lastIndexOf(","));
		s.append("]");
		str = s.toString();
		return str;
	}
}
