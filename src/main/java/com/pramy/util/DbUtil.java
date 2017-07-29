package com.pramy.util;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbUtil
{
/*    private static SqlSessionFactory sessionFactory;

    static
    {
        try
        {
            Reader reader = Resources.getResourceAsReader("mybatisConfig.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory()
    {
        return sessionFactory;
    }

    public static SqlSession getSession(){
        return sessionFactory.openSession(true);
    }*/
}