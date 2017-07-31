package com.pramy.service;

import com.pramy.model.User;
import com.pramy.service.lmp.BaseServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/25.
 */

public interface UserService extends BaseService<User> {


    User selectOneByName(User user);
}
