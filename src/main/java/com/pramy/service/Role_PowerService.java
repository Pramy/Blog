package com.pramy.service;

import com.pramy.model.RolePower;
import com.pramy.model.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/25.
 */

public interface Role_PowerService extends BaseService<RolePower> {

    RolePower selectByRoleId(RolePower rolePower);
}
