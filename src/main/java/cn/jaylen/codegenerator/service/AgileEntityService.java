package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.base.BaseService;
import cn.jaylen.codegenerator.entity.AgileEntity;

import java.util.List;
import java.util.Map;

public interface AgileEntityService extends BaseService<AgileEntity> {
    List<AgileEntity> selectAll();
    int deleteByIDs(Long[] ids);
    int saveAgileEntites(Long schemaId, Long conId, String databaseName, String[] tableNames);
    List<Map<String, Object>> getEntityTree(Long schemaId);
    List<AgileEntity> selectBySchemaId(Long schemaId);
}
