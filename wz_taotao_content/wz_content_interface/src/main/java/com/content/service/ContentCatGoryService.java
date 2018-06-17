package com.content.service;

import java.util.List;

import com.taotao.common.EasyUITreeNode;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

public interface ContentCatGoryService {
	List<EasyUITreeNode> getContentCatList(Long parentId);
	TaotaoResult insert(Long parentId,String name);
}
