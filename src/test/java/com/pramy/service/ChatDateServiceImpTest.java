package com.pramy.service;

import com.pramy.model.ChatDate;
import com.pramy.util.PageUtil;
import net.sf.ehcache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;


/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybaits.xml")
public class ChatDateServiceImpTest {

   @Autowired
    private ChatDateService chatDateService;
   @Autowired
   private EhCacheCacheManager ehCacheCacheManager;
    @Test
    public void total() throws Exception {


        PageUtil pageUtil = new PageUtil("12","1",10);
        ChatDate chatDate = new ChatDate();
        chatDate.setSectionId(1);
        Random random = new Random();
        chatDate.setContent(String.valueOf(random.nextInt(10)));
        chatDate.setUserName("hello");
        System.out.println(ehCacheCacheManager.getClass());
        System.out.println(EhCacheCacheManager.class);
        chatDateService.testCache(1);
        chatDateService.clear();
        chatDateService.testCache(1);
        chatDateService.testCache(1);

/*
        System.out.println("*******************************************");
        System.out.println(chatDateMapper);

        List<ChatDate> list = chatDateMapper.selectList(chatDate,pageUtil);
        System.out.println("********************************************");
        System.out.println(list.size());
        for (ChatDate c: list
             ) {
            System.out.println(c);
        }
*/


    }

}