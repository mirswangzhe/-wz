package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.content.service.ContentService;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbContent;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insert(TbContent tbContent){
		TaotaoResult insertContent = contentService.insertContent(tbContent);
		return insertContent;
	}
}
