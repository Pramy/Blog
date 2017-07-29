package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.MyFileMapper;
import com.pramy.model.MyFile;
import com.pramy.service.MyFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyFileServiceImp extends BaseServiceImp<MyFile> implements MyFileService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyFileMapper myFileMapper;

    @Override
    public BaseMapper<MyFile> getMapper() {
        return myFileMapper;
    }
}
