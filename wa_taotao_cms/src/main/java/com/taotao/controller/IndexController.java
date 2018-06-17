package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.content.service.ContentService;
import com.taotao.common.JsonUtils;
import com.taotao.pojo.Ad1Node;
import com.taotao.pojo.TbContent;




@Controller
public class IndexController {
	
	@Value("${AD1_CONTENT_CID}")
	private Long AD1_CONTENT_CID;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		//鍙栧唴瀹瑰垎绫籭d锛屼粠灞炴�ф枃浠朵腑鍙�
		//鏍规嵁鍐呭鍒嗙被id鏌ヨ鍐呭鍒楄〃
		List<TbContent> contentList = contentService.getContentList(AD1_CONTENT_CID);
		List<Ad1Node> ad1NodeList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			Ad1Node node = new Ad1Node();
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			node.setSrc(tbContent.getPic());
			node.setSrcB(tbContent.getPic2());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			
			ad1NodeList.add(node);
		}
		String json = JsonUtils.objectToJson(ad1NodeList);
		model.addAttribute("ad1", json);
		return "index";
	}
}
