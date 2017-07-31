package com.pramy.service.lmp;


import com.pramy.dao.BaseMapper;
import com.pramy.dao.SectionMapper;
import com.pramy.model.Section;
import com.pramy.service.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImp extends BaseServiceImp<Section> implements SectionService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SectionMapper sectionMapper;
    @Override
    public BaseMapper<Section> getMapper() {
        return sectionMapper;
    }
}
