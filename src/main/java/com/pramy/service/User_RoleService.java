package com.pramy.service;

import com.pramy.model.UserRole;
import org.springframework.stereotype.Service;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/25.
 */

public interface User_RoleService extends BaseService<UserRole>{

    UserRole selectByUserId(UserRole userRole);
}
