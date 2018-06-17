package com.sso.service;

import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserRegisterService {
	TaotaoResult checkUser(String param,int type);
	TaotaoResult createUser(TbUser tbUser);
}
