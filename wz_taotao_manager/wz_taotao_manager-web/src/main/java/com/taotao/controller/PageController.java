package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUIDataGridResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult pageList(Integer page,Integer rows){
		EasyUIDataGridResult easyUIDataGridResult=itemService.pageList(page,rows);
		return easyUIDataGridResult;
	}
}
