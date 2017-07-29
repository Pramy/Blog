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



    @Cacheable(value = "index",key = "#root.method")
    public void testCache(int id){
        System.out.println("查询数据");
    }

    @CacheEvict(value = "index" ,allEntries = true,beforeInvocation = true)
    public void clear() {
        System.out.println("清除缓存");
    }

}
