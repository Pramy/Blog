package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.ChatDateMapper;
import com.pramy.model.ChatDate;
import com.pramy.service.ChatDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ChatDateServiceImp extends BaseServiceImp<ChatDate> implements ChatDateService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChatDateMapper chatDateMapper;

    @Override
    public BaseMapper getMapper() {
        return chatDateMapper;
    }




}
