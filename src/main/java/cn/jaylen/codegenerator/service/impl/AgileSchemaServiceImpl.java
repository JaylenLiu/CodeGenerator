package cn.jaylen.codegenerator.service.impl;
import cn.jaylen.codegenerator.base.BaseServiceImpl;
import cn.jaylen.codegenerator.dao.AgileComponentMapper;
import cn.jaylen.codegenerator.dao.AgileEntityMapper;
import cn.jaylen.codegenerator.dao.AgileSchemaMapper;
import cn.jaylen.codegenerator.entity.AgileEntity;
import cn.jaylen.codegenerator.entity.AgileSchema;
import cn.jaylen.codegenerator.entity.example.AgileComponentExample;
import cn.jaylen.codegenerator.entity.example.AgileEntityExample;
import cn.jaylen.codegenerator.entity.example.AgileSchemaExample;
import cn.jaylen.codegenerator.service.AgileSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AgileSchemaServiceImpl extends BaseServiceImpl<AgileSchema> implements AgileSchemaService {
    @Resource
    AgileSchemaMapper agileSchemaMapper;

    @Resource
    AgileEntityMapper entityMapper;

    @Resource
    AgileComponentMapper componentMapper;

    @Override
    public List<AgileSchema> selectAll(){
        AgileSchemaExample example = new AgileSchemaExample();
        return agileSchemaMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public int deleteByIDs(Long[] ids){
        for (int i = 0; i < ids.length; i++) {
            deleteAgileSchema(ids[i]);
        }
        return ids.length;
    }

    @Override
    @Transactional
    public int deleteAgileSchema(long id) {
        // 获取实体信息
        AgileEntityExample entityExample = new AgileEntityExample();
        entityExample.createCriteria().andSchemaIdEqualTo(id);
        List<AgileEntity> entityList = entityMapper.selectByExample(entityExample);
        if (entityList.size() != 0) {
            // 获取实体ids
            List<Long> entityIds = entityList.stream().map(AgileEntity::getId).collect(Collectors.toList());

            // 删除所有的component
            AgileComponentExample componentExample = new AgileComponentExample();
            componentExample.createCriteria().andEntityIdIn(entityIds);
            componentMapper.deleteByExample(componentExample);
        }

        // 删除entity信息
        entityMapper.deleteByExample(entityExample);
        // 删除schema信息
        return agileSchemaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> selectSchemaTree() {
        List<Map<String, Object>> schemaTree = new LinkedList<>();

        List<AgileSchema> schemaList = selectAll();
        if (schemaList.size() != 0) {
            schemaList.stream().forEach(schema->{
                Map<String, Object> childMap = new HashMap<>();
                childMap.put("label", schema.getSchemaName());
                childMap.put("id", schema.getId());
                childMap.put("children", Collections.emptyList());
                schemaTree.add(childMap);
            });
        }

        return schemaTree;
    }
}
