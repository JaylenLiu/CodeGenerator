package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.DatabaseConnection;
import cn.jaylen.codegenerator.service.DatabaseConnectionService;
import cn.jaylen.codegenerator.util.DatabaseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DatabaseConnectionController{
    @Autowired
    DatabaseConnectionService service;

    @GetMapping(value = "/databaseTree")
    public Message databaseTree() {
        return Message.successMessage(service.databaseTree());
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/databaseConnection")
    public Message selectById(Long id){
        return Message.successMessage(service.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @GetMapping("/databaseConnections")
    public Message selectAll(){
        return Message.successMessage(service.selectAll());
    }

    @ApiOperation(value = "新增DatabaseConnection", notes = "新增DatabaseConnection")
    @PostMapping("/databaseConnection")
    public Message insertDatabaseConnection(DatabaseConnection databaseConnection){
        return Message.successMessage(service.insert(databaseConnection));
    }

    @ApiOperation(value = "删除DatabaseConnection", notes = "删除DatabaseConnection")
    @DeleteMapping("/databaseConnection")
    public Message deleteDatabaseConnection(Long id){
        return Message.successMessage(service.deleteByPrimaryKey(id));
    }

    @ApiOperation(value = "删除多个", notes = "删除多个")
    @DeleteMapping("/databaseConnections")
    public Message deleteByIds(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.deleteByIDs(ids));
        }
    }

    @ApiOperation(value = "更新DatabaseConnection", notes = "更新DatabaseConnection")
    @PutMapping(value = "/databaseConnection")
    public Message updateDatabaseConnection(DatabaseConnection databaseConnection){
        return Message.successMessage(service.updateByPrimaryKey(databaseConnection));
    }

    /**
     *
     * @param conId : 数据库连接表id
     * @param databaseName ： 数据库名称
     * @param tableName： 表名
     * @return
     */
    @ApiOperation(value = "获取表字段信息")
    @GetMapping(value = "/fields")
    public Message getFieldByCondition(Long conId, String databaseName, String tableName){
        DatabaseConnection connection = service.selectByPrimaryKey(conId);
        DatabaseUtil util = new DatabaseUtil(connection);
        return Message.successMessage(util.getTableColumns(databaseName,tableName));
    }

    /**
     *
     * @param conId : 数据库连接
     * @return
     */
    @ApiOperation(value = "获取该连接下所有数据库信息")
    @GetMapping(value = "/databases")
    public Message getAllDatabases(Long conId){
        DatabaseConnection connection = service.selectByPrimaryKey(conId);
        DatabaseUtil util = new DatabaseUtil(connection);
        LinkedList<String> databases = util.getDatabases();

        // 过滤掉系统表
        List<String> result = databases.stream().filter(item->{
            return !item.equals("information_schema") && !item.equals("mysql") && !item.equals("sys") &&
                    !item.equals("performance_schema") && !item.equals("sakila");
        }).collect(Collectors.toList());

        return Message.successMessage(result);
    }


    /**
     *
     * @param conId : 数据库连接id
     * @param databaseName : 数据库名
     * @return
     */
    @ApiOperation(value = "获取某个数据库下面的所有表信息")
    @GetMapping(value = "/tables")
    public Message getAllTables(Long conId, String databaseName){
        DatabaseConnection connection = service.selectByPrimaryKey(conId);
        DatabaseUtil util = new DatabaseUtil(connection);
        return Message.successMessage(util.getAllTableList(databaseName));
    }

}
