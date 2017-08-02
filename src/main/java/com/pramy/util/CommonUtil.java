package com.pramy.util;

import java.io.IOException;
import java.io.Reader;

import com.pramy.common.CommonResult;
import com.pramy.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CommonUtil {

    public static CommonResult getCommon(boolean flag, String message){
        return new CommonResult(flag,message);
    }
    public static CommonResult getCommon(boolean flag, String message,String url){
        return new CommonResult(flag,message,url);
    }
}