package com.pramy.service;

import com.pramy.util.PageUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

     T selectOneById(int id);

     int delete(T t);

     int update(T t);


}
