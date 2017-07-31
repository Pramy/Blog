package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.RolePowerMapper;
import com.pramy.model.RolePower;
import com.pramy.model.UserRole;
import com.pramy.service.Role_PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("se")
public class Role_PowerServiceImp extends BaseServiceImp<RolePower> implements Role_PowerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RolePowerMapper rolePowerMapper;

    @Override
    public BaseMapper<RolePower> getMapper() {
        return rolePowerMapper;
    }

    @Override
    public RolePower selectByRoleId(RolePower rolePower) {
        return rolePowerMapper.selectByRoleId(rolePower);
    }
}
