package com.content.service;

import java.util.List;

import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	TaotaoResult insertContent(TbContent content);
	List<TbContent> getContentList(long cid);
}
