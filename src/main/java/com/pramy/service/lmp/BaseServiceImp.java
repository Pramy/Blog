package com.pramy.service.lmp;

import com.pramy.dao.BaseMapper;
import com.pramy.service.BaseService;
import com.pramy.util.PageUtil;
import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/23.
 */

public abstract class BaseServiceImp<T> implements BaseService<T> {

    public abstract BaseMapper<T> getMapper();


    @Override
    public int add(T t) {
        return  getMapper().insert(t);
    }

    @Override
    public int total(T t) {
        return getMapper().selectCount(t);
    }

    @Override
    public List<T> selectList(T t, PageUtil pageUtil) {
        return getMapper().selectList(t,pageUtil);
    }

    @Override
    public T selectOneById(int id) {
        return (T) getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int delete(T t) {
        return getMapper().deleteByPrimaryKey(t);
    }

    @Override
    public int update(T t) {
        return getMapper().updateByPrimaryKey(t);
    }


}
