package cn.jaylen.codegenerator.service.impl;

import cn.jaylen.codegenerator.base.BaseServiceImpl;
import cn.jaylen.codegenerator.dao.AgileComponentMapper;
import cn.jaylen.codegenerator.entity.AgileComponent;
import cn.jaylen.codegenerator.entity.example.AgileComponentExample;
import cn.jaylen.codegenerator.service.AgileComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AgileComponentServiceImpl extends BaseServiceImpl<AgileComponent> implements AgileComponentService {
    @Autowired
    AgileComponentMapper agileComponentMapper;

    @Override
    public List<AgileComponent> selectAll(){
    AgileComponentExample example = new AgileComponentExample();
        return agileComponentMapper.selectByExample(example);
    }

    @Override
    public int deleteByIDs(Long[] ids){
    AgileComponentExample example = new AgileComponentExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return agileComponentMapper.deleteByExample(example);
    }

    @Override
    public AgileComponent selectByEntityName(Long entityId, String fieldName) {
        AgileComponentExample componentExample = new AgileComponentExample();
        componentExample.createCriteria().andEntityIdEqualTo(entityId).andFieldNameEqualTo(fieldName);
        return agileComponentMapper.selectByExample(componentExample).get(0);
    }
}
