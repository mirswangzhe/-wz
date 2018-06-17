package com.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sso.service.UserRegisterService;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.UserMapper;
import com.taotao.pojo.TbUser;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
/**
 * 用户注册
 * @author wz
 *
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public TaotaoResult checkUser(String param, int type) {
		Example example=new Example(TbUser.class);
		Criteria createCriteria = example.createCriteria();
		TbUser user=new TbUser();
		//判断校验的数据类型
		//1,2,3分别代表username,phone,email
		if(type==1){
			createCriteria.andEqualTo(user.getUsername(),param);
		}else if (type==2) {
			createCriteria.andEqualTo(user.getPhone(),param);
		}else if(type==3){
			createCriteria.andEqualTo(user.getEmail(),param);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if(list==null||list.size()==0){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}
	@Override
	public TaotaoResult createUser(TbUser tbUser) {
		if(StringUtils.isBlank(tbUser.getUsername())
				||StringUtils.isBlank(tbUser.getPassword())){
			return TaotaoResult.build(400, "用户名和密码不能为空");
		}
		TaotaoResult checkUser = checkUser(tbUser.getUsername(),1);
		Boolean flag = (Boolean) checkUser.getData();
		if(!flag){
			return TaotaoResult.build(400, "用户名不能重复");
		}
		checkUser = checkUser(tbUser.getPhone(),2);
		if(!(boolean) checkUser.getData()){
			return TaotaoResult.build(400, "shoujihao不能重复");
		}
		checkUser = checkUser(tbUser.getEmail(),3);
		if(!(boolean) checkUser.getData()){
			return TaotaoResult.build(400, "shoujihao不能重复");
		}
		//手机号，邮箱  一样
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		//密码进行MD5加密
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
		tbUser.setPassword(md5DigestAsHex);
		userMapper.insert(tbUser);
		return TaotaoResult.ok();
	}

}
