package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.base.BaseService;
import cn.jaylen.codegenerator.entity.AgileSchema;

import java.util.List;
import java.util.Map;

public interface AgileSchemaService extends BaseService<AgileSchema> {
    List<AgileSchema> selectAll();
    int deleteByIDs(Long[] ids);
    int deleteAgileSchema(long id);
    List<Map<String, Object>> selectSchemaTree();
}
