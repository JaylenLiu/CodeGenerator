package cn.jaylen.codegenerator.service.impl;

import cn.jaylen.codegenerator.base.BaseServiceImpl;
import cn.jaylen.codegenerator.dao.AgileComponentMapper;
import cn.jaylen.codegenerator.dao.AgileEntityMapper;
import cn.jaylen.codegenerator.dao.DatabaseConnectionMapper;
import cn.jaylen.codegenerator.entity.AgileComponent;
import cn.jaylen.codegenerator.entity.AgileEntity;
import cn.jaylen.codegenerator.entity.DatabaseConnection;
import cn.jaylen.codegenerator.entity.example.AgileEntityExample;
import cn.jaylen.codegenerator.service.AgileEntityService;
import cn.jaylen.codegenerator.util.DatabaseUtil;
import cn.jaylen.codegenerator.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AgileEntityServiceImpl extends BaseServiceImpl<AgileEntity> implements AgileEntityService {
    @Autowired
    AgileEntityMapper agileEntityMapper;

    @Autowired
    DatabaseConnectionMapper connectionMapper;

    @Autowired
    AgileComponentMapper componentMapper;

    @Override
    public List<AgileEntity> selectAll(){
        AgileEntityExample example = new AgileEntityExample();
        return agileEntityMapper.selectByExample(example);
    }

    @Override
    public int deleteByIDs(Long[] ids){
        AgileEntityExample example = new AgileEntityExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return agileEntityMapper.deleteByExample(example);
    }

    @Override
    public int saveAgileEntites(Long schemaId, Long conId, String databaseName, String[] tableNames) {
        for (String tableName :
                tableNames) {
            AgileEntity agileEntity = new AgileEntity();
            agileEntity.setSchemaId(schemaId);
            agileEntity.setConId(conId);
            agileEntity.setDatabaseName(databaseName);
            agileEntity.setTableName(tableName);
            agileEntity.setClassName(StringUtils.toUpperCaseFirstOne(StringUtils.lineToHump(tableName)));
            agileEntity.setEntityType(1);
            agileEntity.preInsert();
            agileEntityMapper.insertSelective(agileEntity);

            saveComponent(conId, databaseName, tableName, agileEntity.getId());
        }
        return tableNames.length;
    }

    /**
     * 插入实体时，自动插入组件信息
     * @param conId： 数据库连接id
     * @param databaseName : 数据库名称
     * @param tableName ： 表名
     * @param entityId ： 实体id
     */
    private void saveComponent(Long conId, String databaseName, String tableName, Long entityId){
        DatabaseConnection connection = connectionMapper.selectByPrimaryKey(conId);
        DatabaseUtil util = new DatabaseUtil(connection);
        List<Map<String, Object>> columns = util.getTableColumns(databaseName,tableName);
        List<Map<String, Object>> primaryKeys = util.getPrimaryKeys(databaseName,tableName);
        if (columns.size() != 0) {
            columns.forEach(column-> {
                String columnName = StringUtils.lineToHump(column.get("columnName").toString());
                String remarks = column.get("remarks").toString();
                AgileComponent component = new AgileComponent();
                component.setEntityId(entityId);
                component.setFieldName(columnName);
                component.setLabel(remarks);
                component.setPlaceholder(remarks);
                component.preInsert();

                primaryKeys.forEach(map -> {
                    if (map.get("column_name").toString().equalsIgnoreCase(columnName)){
                        component.setIsVisibled(false);
                    }
                });

                String typeName = column.get("typeName").toString().toLowerCase();
                switch (typeName) {
                    case "date" : {
                        component.setComponentType("datePicker");
                        break;
                    }
                    case "datetime":{
                        component.setComponentType("dateTimePicker");
                        break;
                    }
                    default:{}
                }
                componentMapper.insertSelective(component);
            });
        }
    }

    @Override
    public List<Map<String, Object>> getEntityTree(Long schemaId) {
        // 获取schema对应的entity
        AgileEntityExample example = new AgileEntityExample();
        example.createCriteria().andSchemaIdEqualTo(schemaId);
        List<AgileEntity> entityList = agileEntityMapper.selectByExample(example);

        List<Map<String, Object>> databaseTree = new LinkedList<>();

        if (entityList.size() != 0) {
            // 构建数据库管理工具
            DatabaseUtil util = new DatabaseUtil(connectionMapper.selectByPrimaryKey(entityList.get(0).getConId()));

            entityList.stream().forEach(agileEntity->{
                HashMap<String, Object> tableMap = new HashMap<>();
                List<Map<String, Object>> childList = new ArrayList<>();
                tableMap.put("id", agileEntity.getId());
                tableMap.put("label", agileEntity.getClassName());
                tableMap.put("nodeType", "table");
                tableMap.put("children", childList);

                // 获取fields
                List<Map<String, Object>> fieldList = util.getTableColumns(agileEntity.getDatabaseName(),agileEntity.getTableName());
                fieldList.stream().forEach(field -> {
                    HashMap<String, Object> fieldMap = new HashMap<>();
                    fieldMap.put("label", StringUtils.lineToHump(field.get("columnName").toString()));
                    fieldMap.put("nodeType", "field");
                    fieldMap.put("entityId", agileEntity.getId());
                    childList.add(fieldMap);
                });
                databaseTree.add(tableMap);
            });
        } else {
            return Collections.emptyList();
        }

        return databaseTree;
    }

    @Override
    public List<AgileEntity> selectBySchemaId(Long schemaId) {
        AgileEntityExample example = new AgileEntityExample();
        example.createCriteria().andSchemaIdEqualTo(schemaId);
        return  agileEntityMapper.selectByExample(example);
    }
}
