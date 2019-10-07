package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

@Controller
@RequestMapping("/detail")
public class ShowItemDetailController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("")
	public String showItemDetail(int id, Model model) {
		
		Item item = itemService.findByID(id);
		model.addAttribute("item", item);
		return "detail";
	}
}
