package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.PowerMapper;
import com.pramy.model.Power;
import com.pramy.service.PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerServiceImp extends BaseServiceImp<Power> implements PowerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PowerMapper powerMapper;
    @Override
    public BaseMapper<Power> getMapper() {
        return powerMapper;
    }
}
