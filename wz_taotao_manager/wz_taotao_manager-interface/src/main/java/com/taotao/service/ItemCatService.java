package com.taotao.service;

import java.util.List;

import com.taotao.common.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getParentId(Long parentId);
	
}
