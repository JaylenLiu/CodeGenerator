package cn.jaylen.codegenerator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * @author ljl
 * @create 2018-04-12 11:55
 * @desc 数据库操作工具类
 **/
public class DatabaseUtil {
    private static Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);

    private DatabaseMetaData dbMetaData = null;
    private Connection con = null;

    /**
     * 根据传入的对象构建元数据
     */
    public DatabaseUtil(String ip, String port, String username, String password){
        try {
            String url = "jdbc:mysql://" + ip + ":" + port;
            con = DriverManager.getConnection(url, username, password);
            dbMetaData = con.getMetaData();
        } catch (Exception e) {
            logger.error("数据库连接获取失败", e.getMessage());
        }
    }

    public DatabaseMetaData getDbMetaData() {
        return dbMetaData;
    }

    public Connection getCon() {
        return con;
    }

    /**
     * 获得某数据库下面的所有表
     */
    public LinkedList<String> getAllTableList(String catalog) {
        LinkedList<String> tableNames = new LinkedList<>();
        try {
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
            String[] types = { "TABLE" };
            ResultSet rs = dbMetaData.getTables(catalog, catalog, "%", types);
            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }
            logger.info("获取" + catalog + "下的表：", tableNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }

    /**
     * 获取该数据库连接下面的所有数据库
     * @return
     * @throws SQLException
     */
    public LinkedList<String> getDatabases() {
        LinkedList<String> list = new LinkedList<>();
        try{
            ResultSet rs=dbMetaData.getCatalogs();
            while(rs.next()){
                list.add(rs.getString("TABLE_CAT"));
            }
            logger.info("获取所有数据库名：", list.toString());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("获取数据库列表失败！", e.getMessage());
        }
        return list;
    }

    /**
     * 获得表或视图中的所有列信息
     */
    public List<Map<String, Object>> getTableColumns(String schemaName, String tableName) {
        LinkedList<Map<String, Object>> list = new LinkedList<>();
        try{
            ResultSet rs = dbMetaData.getColumns(schemaName, schemaName, tableName, "%");
            while (rs.next()){
                HashMap<String, Object> map = new HashMap<>();
                map.put("columnName", rs.getString("COLUMN_NAME"));
                map.put("typeName", rs.getString("TYPE_NAME"));
                map.put("columnSize", rs.getInt("COLUMN_SIZE"));
                map.put("decimalDigits", rs.getInt("DECIMAL_DIGITS"));
                map.put("nullable", rs.getInt("NULLABLE") == 0 ? "false" : "true");
                map.put("remarks", rs.getString("REMARKS"));
                list.add(map);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获得一个表的索引信息
     */
    public List<HashMap<String, Object>> getIndexInfo(String schemaName, String tableName) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        try{
            ResultSet rs = dbMetaData.getIndexInfo(schemaName, schemaName, tableName, false, true);
            while (rs.next()){
                HashMap<String, Object> map = new HashMap<>();
                map.put("non_unique", rs.getBoolean("NON_UNIQUE"));
                map.put("index_name", rs.getString("INDEX_NAME"));
                map.put("type", rs.getShort("TYPE"));
                map.put("ordinal_position", rs.getShort("ORDINAL_POSITION"));
                map.put("column_name", rs.getString("COLUMN_NAME"));
                map.put("asc_or_desc", rs.getString("ASC_OR_DESC"));
                list.add(map);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void close(){
       if (con != null) {
           try {
               con.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }

    /**
     * 将表中字段类型转化成java 类型
     * @param columnType
     * @return
     */
    public static String getClassType(String columnType){
        switch (columnType) {
            case "TEXT" :
            case "VARCHAR" : return "String";
            case "TINYINT":
            case "INT": return  "Integer";
            case "FLOAT" : return "Float";
            case "DECIMAL" :return "BigDecimal";
            case "DOUBLE" : return "Double";
            case "DATE" :
            case "DATETIME" : return "Date";
            case "CHAR" : return "Character";
            default:return "String";
        }
    }
}
