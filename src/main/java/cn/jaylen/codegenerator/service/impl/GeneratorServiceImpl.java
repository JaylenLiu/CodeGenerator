package cn.jaylen.codegenerator.service.impl;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.common.Message;
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
import cn.jaylen.codegenerator.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

//    @Value("${outputPath}")
//    private String outputPath;

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
                generateBackendCode(packagePath, moduleName, tableNames, jdbcMap);
            }
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
        GeneratorUtil util = GeneratorUtil.getGeneratorUtil(packagePath, moduleName);
        try{
            util.generateIndex(entityName,componentList);
            return true;
        } catch (Exception e) {
            logger.error("生成前台代码失败！");
            return false;
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
    private boolean generateBackendCode(String packagePath, String moduleName, List<String> tableNames, Map<String, Object> jdbc ){
        GeneratorUtil util = GeneratorUtil.getGeneratorUtil(packagePath, moduleName);
        try{
            // 生成mybatis-generator配置文件
            util.generateConfig(tableNames, jdbc);
            // 执行mybatis-generator代码生成器
            util.mybatis_generator();

            tableNames.forEach((item)->{
                item = StringUtils.lineToHump(item);
                util.generateController(item);
                util.generateService(item);
                util.generateServiceImpl(item);
            });

            // 文件压缩并下载
//            ZipUtils.toZip(outputPath, SpringContextUtil.getResponse().getOutputStream(),true);
            // 删除临时目录
//            ZipUtils.delFolder(outputPath);
            return true;
        } catch (Exception e){
            logger.error("生成后端代码失败", e);
            return false;
        }
    }

}
