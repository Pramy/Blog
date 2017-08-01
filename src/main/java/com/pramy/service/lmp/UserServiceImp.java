package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.UserMapper;
import com.pramy.model.User;
import com.pramy.service.UserService;
import com.pramy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends BaseServiceImp<User> implements UserService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private  UserMapper userMapper;

	@Override
	public BaseMapper<User> getMapper() {
		return userMapper;
	}

	public boolean isExit(User user){
		boolean flag = false;
		User user1=userMapper.selectByUserName(user);
		if(!StringUtil.isEmpty(user1))
			flag=true;
		return flag;
	}

	@Override
	public User selectOneByName(User user) {
		return userMapper.selectByUserName(user);
	}
}
