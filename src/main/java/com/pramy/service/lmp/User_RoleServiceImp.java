package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.UserRoleMapper;
import com.pramy.model.UserRole;
import com.pramy.service.User_RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_RoleServiceImp extends BaseServiceImp<UserRole> implements User_RoleService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseMapper<UserRole> getMapper() {
        return userRoleMapper;
    }

    @Override
    public UserRole selectByUserId(UserRole userRole) {
        return userRoleMapper.selectByUserId(userRole);
    }
}
