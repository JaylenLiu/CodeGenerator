package cn.jaylen.codegenerator.service.impl;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.common.exception.CustomException;
import cn.jaylen.codegenerator.common.exception.HttpCodeEnum;
import cn.jaylen.codegenerator.common.generator.GeneratorUtil;
import cn.jaylen.codegenerator.dao.AgileComponentMapper;
import cn.jaylen.codegenerator.dao.AgileEntityMapper;
import cn.jaylen.codegenerator.dao.DatabaseConnectionMapper;
import cn.jaylen.codegenerator.entity.AgileComponent;
import cn.jaylen.codegenerator.entity.AgileEntity;
import cn.jaylen.codegenerator.entity.AgileSchema;
import cn.jaylen.codegenerator.entity.DatabaseConnection;
import cn.jaylen.codegenerator.entity.example.AgileComponentExample;
import cn.jaylen.codegenerator.entity.example.AgileEntityExample;
import cn.jaylen.codegenerator.service.AgileSchemaService;
import cn.jaylen.codegenerator.service.GeneratorService;
import cn.jaylen.codegenerator.util.DatabaseUtil;
import cn.jaylen.codegenerator.util.SpringContextUtil;
import cn.jaylen.codegenerator.util.StringUtils;
import cn.jaylen.codegenerator.util.ZipUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ljl
 * @create 2018-09-03 13:52
 * @desc serviceImpl
 **/
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Value("${outputPath}")
    private String outputPath;

    private Logger logger = (Logger) LoggerFactory.getLogger(GeneratorServiceImpl.class);

    @Autowired
    private AgileSchemaService schemaService;

    @Autowired
    AgileEntityMapper agileEntityMapper;

    @Autowired
    AgileComponentMapper agileComponentMapper;

    @Autowired
    DatabaseConnectionMapper connectionMapper;

    @Override
    public synchronized Message generateCode(Long schemaId, String[] generateList) {
        String packagePath;
        String moduleName;

        // 获取schema信息
        AgileSchema agileSchema = schemaService.selectByPrimaryKey(schemaId);
        if (agileSchema == null) {
            return Message.errorMessage(500, "方案信息不存在！");
        } else {
            packagePath = agileSchema.getPackagePath();
            moduleName = agileSchema.getModuleName();
        }

        // 获取entity信息
        AgileEntityExample entityExample = new AgileEntityExample();
        entityExample.createCriteria().andSchemaIdEqualTo(schemaId);
        List<AgileEntity> entityList = agileEntityMapper.selectByExample(entityExample);

        Map<String, Object> jdbcMap = getJDBCMap(schemaId);
        List<String> tableNames;
//        List<Long> entityIds;
        if (jdbcMap == null) {
            return Message.errorMessage(500, "该方案下没有实体信息！");
        } else {
            tableNames = entityList.stream().map(AgileEntity::getTableName).collect(Collectors.toList());
//            entityIds = entityList.stream().map(AgileEntity::getId).collect(Collectors.toList());
        }

        for (String generateType : generateList) {
            if ("web".equals(generateType)) {
                entityList.forEach(entity -> {
                    AgileComponentExample componentExample = new AgileComponentExample();
                    componentExample.createCriteria().andEntityIdEqualTo(entity.getId());
                    List<AgileComponent> componentList = agileComponentMapper.selectByExample(componentExample);
                    generateWebCode(packagePath, moduleName, StringUtils.toLowerCaseFirstOne(entity.getClassName()), componentList);
                });
            } else if ("java".equals(generateType)) {
                // 生成后端代码
                generateBackendCode(packagePath, moduleName, tableNames, jdbcMap, entityList.get(0).getConId(), entityList.get(0).getDatabaseName());
            }
        }
        // 文件压缩并下载
        try {
            SpringContextUtil.getResponse().setHeader("Content-disposition", "attachment; filename=code.zip");
            ServletOutputStream outputStream = SpringContextUtil.getResponse().getOutputStream();
            ZipUtils.toZip(outputPath , outputStream,true);
            // 删除临时目录
            ZipUtils.delFolder(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Message.successMessage(1);
    }

    /**
     * @Description: 获取jdbc 配置信息
     * @auther: ljl
     * @date: 2018/9/30 10:19
     */
    private Map<String, Object> getJDBCMap(Long schemaId){
        // 获取entity信息
        AgileEntityExample entityExample = new AgileEntityExample();
        entityExample.createCriteria().andSchemaIdEqualTo(schemaId);
        List<AgileEntity> entityList = agileEntityMapper.selectByExample(entityExample);
        if (entityList.size() == 0) {
            return null;
        } else {
            HashMap<String, Object> jdbcMap = new HashMap<>();
            Long conId = entityList.get(0).getConId();
            String databaseName = entityList.get(0).getDatabaseName();
            DatabaseConnection connection = connectionMapper.selectByPrimaryKey(conId);

            jdbcMap.put("driver", "com.mysql.jdbc.Driver");
            jdbcMap.put("url", "jdbc:mysql://"+connection.getConnIp()+":"+connection.getConnPort()+"/"+ databaseName);
            jdbcMap.put("username", connection.getConnUsername());
            jdbcMap.put("password", connection.getConnPwd());

            return jdbcMap;
        }
    }

    public boolean generateWebCode(String packagePath, String moduleName, String entityName, List<AgileComponent> componentList){
        GeneratorUtil util = GeneratorUtil.getGeneratorUtil(outputPath,packagePath);
        try{
            util.generateIndex(entityName,componentList);
            return true;
        } catch (Exception e) {
            logger.error("生成前台代码失败！",e);
            throw new CustomException("生成前台代码失败！", HttpCodeEnum.HTTP_500.getCode());
        }
    }

    /**
     * 生成后端代码
     * @param packagePath： 包路径
     * @param moduleName： 模块名称
     * @param tableNames： 表名
     * @param jdbc ： 数据库连接信息
     * @return
     */
    private boolean generateBackendCode(String packagePath, String moduleName, List<String> tableNames,
                                        Map<String, Object> jdbc, long conId, String databaseName){
        GeneratorUtil util = GeneratorUtil.getGeneratorUtil(outputPath,packagePath);
        try{
            File dir = new File(outputPath + "/rest/" + packagePath.replace(".", "/"));
            dir.mkdirs();
            // 生成mybatis-generator配置文件
            util.generateConfig(tableNames, jdbc);
            // 执行mybatis-generator代码生成器
            util.mybatis_generator();

            DatabaseConnection connection = connectionMapper.selectByPrimaryKey(conId);
            DatabaseUtil databaseUtil = new DatabaseUtil(connection);

            tableNames.forEach((item)->{
                List<Map<String,Object>> colums = databaseUtil.getTableColumns(databaseName, item);
                List<Map<String,Object>> primarykeys = databaseUtil.getPrimaryKeys(databaseName, item);
                String primaryKeyType = null;
                for (int i = 0; i < colums.size(); i++) {
                    Map<String, Object> map = colums.get(i);
                    map.put("typeName", DatabaseUtil.getClassType(map.get("typeName").toString()));
                    map.put("columnName", StringUtils.lineToHump(map.get("columnName").toString()));
                    if (primarykeys != null && primarykeys.size() > 0) {
                        if (map.get("columnName").toString().equals(primarykeys.get(0).get("column_name").toString())){
                            map.put("primaryKey", true);
                            primaryKeyType = map.get("typeName").toString();
                        }
                    }
                }
                item = StringUtils.lineToHump(item);
                util.generateController(item, primaryKeyType);
                util.generateService(item, primaryKeyType);
                util.generateServiceImpl(item, primaryKeyType);
                util.generateEntity(colums, item, packagePath);
            });
            return true;
        } catch (Exception e){
            logger.error("生成后端代码失败", e);
            return false;
        }
    }

}
