package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.RoleMapper;
import com.pramy.model.Role;
import com.pramy.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
