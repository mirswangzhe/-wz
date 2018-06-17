package com.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.content.service.ContentCatGoryService;
import com.taotao.common.EasyUITreeNode;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ContentCatGoryServiceImpl implements ContentCatGoryService{
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		Example example=new Example(TbContentCategory.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo(parentId);
		List<TbContentCategory> selectByExample = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> resultList=new ArrayList<>();
		for (TbContentCategory tbContentCategory : selectByExample) {
			EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
			easyUITreeNode.setId(tbContentCategory.getId());
			easyUITreeNode.setText(tbContentCategory.getName());
			easyUITreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(easyUITreeNode);
		}
		
		
		return resultList;
	}
	@Override
	public TaotaoResult insert(Long parentId, String name) {
		TbContentCategory tbContentCategory=new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setStatus(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(tbContentCategory);
		TbContentCategory selectByPrimaryKey = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!selectByPrimaryKey.getIsParent()){
			selectByPrimaryKey.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(selectByPrimaryKey);
		}
		return TaotaoResult.ok(tbContentCategory);
	}
	
	
}
