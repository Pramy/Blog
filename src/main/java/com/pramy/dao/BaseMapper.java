package com.pramy.dao;

import com.pramy.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/23.
 */
public interface BaseMapper<T> {
    int deleteByPrimaryKey(T t);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Integer id);


    int selectCount(T t);

    List<T> selectList(@Param("t")T t, @Param("page")PageUtil page);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    int automaticDelete(Date time);

    List <T> automaticDeleteList(Date time);
}
