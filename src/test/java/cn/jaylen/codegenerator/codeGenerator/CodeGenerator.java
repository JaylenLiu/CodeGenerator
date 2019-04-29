package cn.jaylen.codegenerator.codeGenerator;

import cn.jaylen.codegenerator.common.generator.GeneratorUtil;
import cn.jaylen.codegenerator.util.DatabaseUtil;
import cn.jaylen.codegenerator.util.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jaylen
 * @Description:
 * @Date: 2019/1/18 15:34
 */
public class CodeGenerator {

    /**
     * 构建数据库信息
     * @return
     */
    private Map<String,Object> buildJdbcMap(){
        HashMap<String,Object> jdbcMap = new HashMap<>();
        jdbcMap.put("driver", "com.mysql.jdbc.Driver");
        jdbcMap.put("url", "jdbc:mysql://10.200.132.165:3306/pg-wtms");
        jdbcMap.put("username", "root");
        jdbcMap.put("password", "Pioneer@2017");
        return jdbcMap;
    }

    /**
     * 配置需要生成代码的表名
     * @return
     */
    private String[] getTables(){
        String[] tables = new String[]{"project_plan_staff_level"};
        return tables;
    }

    /**
     * 获取数据库表的列信息
     * @param tableName
     * @return
     */
    private List<Map<String,Object>> getColumn(String tableName){
        DatabaseUtil databaseUtil = new DatabaseUtil("10.200.132.165", "3306", "root", "Pioneer@2017");
        List<Map<String,Object>> result = databaseUtil.getTableColumns("pg-wtms", tableName);
        result.forEach(map->{
            map.put("typeName", changeType(map.get("typeName").toString()));
            map.put("columnName", StringUtils.lineToHump(map.get("columnName").toString()));
        });
        return result;
    }

    /**
     * 数据库列类型转换
     * @param colType
     * @return
     */
    private String changeType(String colType) {
        switch (colType) {
            case "CHAR":
            case "VARCHAR" :{
                return "String";
            }
            case "BIGINT UNSIGNED":
            case "BIGINT":{
                return "long";
            }
            case "BIT" :
            case "INT" :{
                return "int";
            }
            case "DATETIME" :
            case "DATE" : {
                return "Date";
            }
            case "DECIMAL" :
            case "NUMBERIC":{
                return "BigDecimal";
            }
            case "FLOAT":
            case "DOUBLE":{
                return "double";
            }
            default:{
                return "String";
            }
        }
    }

    @Test
    public void generator(){
        String packagePath = "cn.pioneer.dcim.wtms";
        GeneratorUtil generatorUtil = GeneratorUtil.getGeneratorUtil("D:/code",packagePath);
        try {
            String[] tables = getTables();
            generatorUtil.generateConfig(tables, buildJdbcMap());
            generatorUtil.mybatis_generator();

            String className;
            for (int i = 0; i < tables.length; i++) {
                className = StringUtils.toUpperCaseFirstOne(StringUtils.lineToHump(tables[i]));
                generatorUtil.generateService(className);
                generatorUtil.generateServiceImpl(className);
                generatorUtil.generateController(className);
                generatorUtil.generateEntity(getColumn(tables[i]), className, packagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
