package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.TaotaoResult;
import com.taotao.search.service.SearchItemService;
/**
 * 一键导入索引库
 * @author wz
 *
 */
@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;
	@RequestMapping("input/index")
	@ResponseBody
	public TaotaoResult getInputIndex() throws Exception{
		TaotaoResult result = searchItemService.inputAllToindex();
		return result;
	}
}
