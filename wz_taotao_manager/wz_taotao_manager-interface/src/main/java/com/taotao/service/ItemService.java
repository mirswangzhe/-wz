package com.taotao.service;

import com.taotao.common.EasyUIDataGridResult;

public interface ItemService {

	EasyUIDataGridResult pageList(Integer page, Integer rows);
}
