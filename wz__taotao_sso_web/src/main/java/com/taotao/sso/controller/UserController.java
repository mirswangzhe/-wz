package com.taotao.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sso.service.UserRegisterService;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 用户注册表现层
 * @author wz
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserRegisterService userRegisterService;
	@RequestMapping(value="/user/check/{param}/{type}",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult userRegister(@PathVariable String param,@PathVariable Integer type){
		TaotaoResult result = userRegisterService.checkUser(param, type);
		
		return result;
	}
	@RequestMapping(value="user/register",method=RequestMethod.POST)
	public TaotaoResult createUser(TbUser user){
		TaotaoResult result = userRegisterService.createUser(user);
		return result;
	}
	
}
