package com.taotao.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EasyUIDataGridResult;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public EasyUIDataGridResult pageList(Integer page, Integer rows) {
		PageHelper.startPage(page,rows);
		List<TbItem> selectAll = itemMapper.selectAll();
	    System.out.println(selectAll);
		PageInfo<TbItem> pageInfo=new PageInfo<>(selectAll);
		EasyUIDataGridResult e=new EasyUIDataGridResult();
		e.setTotal(pageInfo.getTotal());
		e.setRows(selectAll);
		return e;
	}

}
