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
        jdbcMap.put("url", "jdbc:mysql://127.0.0.1:3306/codegenerator");
        jdbcMap.put("username", "root");
        jdbcMap.put("password", "123456");
        return jdbcMap;
    }

    private String[] getTables(){
        String[] tables = new String[]{"agile_component", "agile_entity", "agile_schema"};
        return tables;
    }

    private List<Map<String,Object>> getColumn(String tableName){
        DatabaseUtil databaseUtil = new DatabaseUtil("127.0.0.1", "3306", "root", "123456");
        List<Map<String,Object>> result = databaseUtil.getTableColumns("codegenerator", tableName);
        result.forEach(map->{
            map.put("typeName", changeType(map.get("typeName").toString()));
            map.put("columnName", StringUtils.lineToHump(map.get("columnName").toString()));
        });
        return result;
    }

    private String changeType(String colType) {
        System.out.println(colType);
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
        String packagePath = "cn.jaylen.codegenerator";
        GeneratorUtil generatorUtil = GeneratorUtil.getGeneratorUtil(packagePath);
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
