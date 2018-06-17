package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.content.service.ContentCatGoryService;
import com.taotao.common.EasyUITreeNode;
import com.taotao.common.TaotaoResult;

@Controller
public class ContentCatDatatoryController {
	@Autowired
	private ContentCatGoryService contentCatGoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> categorylist(@RequestParam(value="id",defaultValue="0")Long id){
		List<EasyUITreeNode> list = contentCatGoryService.getContentCatList(id);
		return list;
	}
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult insert(Long parentId,String name){
		TaotaoResult result = contentCatGoryService.insert(parentId, name);
		
		return result;
	}
}
