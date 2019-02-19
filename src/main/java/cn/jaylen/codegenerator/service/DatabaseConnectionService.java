package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.base.BaseService;
import cn.jaylen.codegenerator.entity.DatabaseConnection;

import java.util.List;
import java.util.Map;

public interface DatabaseConnectionService extends BaseService<DatabaseConnection> {
    List<DatabaseConnection> selectAll();
    int deleteByIDs(Long[] ids);
    List<Map<String, Object>> databaseTree();
    List<Map<String, Object>> getFieldByCondition(Long conId, String databaseName, String tableName);
}
