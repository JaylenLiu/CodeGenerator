package cn.jaylen.codegenerator.service.impl;
import cn.jaylen.codegenerator.base.BaseServiceImpl;
import cn.jaylen.codegenerator.dao.DatabaseConnectionMapper;
import cn.jaylen.codegenerator.entity.DatabaseConnection;
import cn.jaylen.codegenerator.entity.example.DatabaseConnectionExample;
import cn.jaylen.codegenerator.service.DatabaseConnectionService;
import cn.jaylen.codegenerator.util.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatabaseConnectionServiceImpl extends BaseServiceImpl<DatabaseConnection> implements DatabaseConnectionService {
    @Autowired
    DatabaseConnectionMapper databaseConnectionMapper;

    @Override
    public List<DatabaseConnection> selectAll(){
    DatabaseConnectionExample example = new DatabaseConnectionExample();
        example.createCriteria().andIdIsNotNull();
        return databaseConnectionMapper.selectByExample(example);
    }

    @Override
    public int deleteByIDs(Long[] ids){
    DatabaseConnectionExample example = new DatabaseConnectionExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return databaseConnectionMapper.deleteByExample(example);
    }

    @Override
    public List<Map<String, Object>> databaseTree() {
        // 获取数据库连接信息
        List<DatabaseConnection> dataconn = selectAll();
        List<Map<String, Object>> databaseTree = new LinkedList<>();
        if (dataconn != null && dataconn.size() != 0) {
            dataconn.stream().forEach((item) -> {
                HashMap<String, Object> conMap = new HashMap<>();
                List<Map<String, Object>> conList = new ArrayList<>();
                conMap.put("id", item.getId());
                conMap.put("label", item.getConnName());
                conMap.put("nodeType", "con");
                conMap.put("children", conList);
                DatabaseUtil util = new DatabaseUtil(item);
                // 获取数据库信息
                util.getDatabases().stream().filter((database) -> !database.equals("information_schema")
                        && !database.equals("mysql") && !database.equals("sys") && !database.equals("performance_schema")).forEach((database) -> {
                    HashMap<String, Object> databaseMap = new HashMap<>();
                    List<Map<String, Object>> databaseList = new ArrayList<>();
                    databaseMap.put("label", database);
                    databaseMap.put("nodeType", "database");
                    databaseMap.put("children", databaseList);
                    conList.add(databaseMap);
                    // 获取表信息
                    util.getAllTableList(database).stream().forEach((table) -> {
                        HashMap<String, Object> tableMap = new HashMap<>();
                        tableMap.put("conId", item.getId());
                        tableMap.put("databaseName", database);
                        tableMap.put("label", table);
                        tableMap.put("nodeType", "table");
                        tableMap.put("children", Collections.emptyList());
                        databaseList.add(tableMap);
                    });
                });
                databaseTree.add(conMap);
            });
        }
        return databaseTree;
    }

    @Override
    public List<Map<String, Object>> getFieldByCondition(Long conId, String databaseName, String tableName) {
        DatabaseConnection connection = selectByPrimaryKey(conId);
        DatabaseUtil util = new DatabaseUtil(connection);
        return util.getTableColumns(databaseName,tableName);
    }
}
