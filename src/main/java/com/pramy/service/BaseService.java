package com.pramy.service;

import com.pramy.util.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/23.
 */
public interface BaseService<T> {

    int add(T t);

    int total(T t);

    List<T> selectList(T t, PageUtil pageUtil);

     T selectOne(int id);

     int delete(T t);

     int update(T t);

     T selectOne(T t);
}
