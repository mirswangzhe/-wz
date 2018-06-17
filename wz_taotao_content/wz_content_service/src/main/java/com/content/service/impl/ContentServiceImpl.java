package com.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.content.service.ContentService;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.TbContent;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private ContentMapper contentMapper;
	@Override
	public TaotaoResult insertContent(TbContent content) {
		content.setUpdated(new Date());
		content.setCreated(new Date());
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}
	@Override
	public List<TbContent> getContentList(long cid) {
		Example example=new Example(TbContent.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		return list;
	}

}
