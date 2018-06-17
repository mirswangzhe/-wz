package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.EasyUITreeNode;
import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service

public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Override
	public List<EasyUITreeNode> getParentId(Long parentId) {
		Example example=new Example(TbItemCat.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo(parentId);
		List<TbItemCat> selectByExample = itemCatMapper.selectByExample(example);
		List<EasyUITreeNode> lists=new ArrayList<>();
		for (TbItemCat tbItemCat : selectByExample) {
			EasyUITreeNode treeNode=new EasyUITreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			lists.add(treeNode);
		}
		return lists;
	}

}
