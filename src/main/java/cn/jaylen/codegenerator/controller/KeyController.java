package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysKeyname;
import cn.jaylen.codegenerator.entity.SysKeyvalue;
import cn.jaylen.codegenerator.service.KeyService;
import cn.jaylen.codegenerator.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ljl
 * @create 2018-08-08 11:20
 * @desc 键值管理：字典管理
 **/
@Api(value = "字典管理")
@RestController
public class KeyController {

    @Autowired
    KeyService service;

    @ApiOperation(value = "根据名称获取字典数据", notes = "根据名称获取字典数据")
    @ApiImplicitParam(name = "keyname", value = "键名", paramType = "query", required = true, dataType = "String")
    @GetMapping(value = "/keyValuesByName")
    public Message getKeyValuesByKeyname(String keyname){
        if (StringUtils.isNullOrEmpty(keyname)){
            return Message.nullParamsMessage();
        } else {
            return service.getKeyValuesByKeyname(keyname);
        }
    }

    @ApiOperation(value = "根据键名id获取字典数据", notes = "根据键名id获取字典数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keynameId", value = "键名id", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "searchKey", value = "查询条件", paramType = "query", required = true, dataType = "String")
    })
    @GetMapping(value = "/keyValuesByKeynameId")
    public Message getKeyvaluesByKeynameId(Long keynameId, String searchKey){
        if (keynameId == null) {
            return Message.nullParamsMessage();
        } else {
            return service.getKeyvaluesByKeynameId(keynameId, searchKey);
        }
    }

    @ApiOperation(value = "根据键名id获取字典数据")
    @GetMapping(value = "/keynames")
    public Message getAllKeynames(){
        return Message.successMessage(service.getAllKeynames());
    }

    @ApiOperation(value = "新增键名", notes = "新增键名")
    @PostMapping(value = "keyname")
    public Message saveKeyname(SysKeyname keyname){
        if (StringUtils.isNullOrEmpty(keyname)){
            return Message.nullParamsMessage();
        } else {
            return service.saveKeyname(keyname);
        }
    }

    @ApiOperation(value = "删除键名", notes = "删除键名")
    @DeleteMapping(value = "/keynames")
    public Message deleteKeyname(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.deleteKeyname(ids);
        }
    }

    @ApiOperation(value = "更新键名", notes = "更新键名")
    @PutMapping(value = "/keyname")
    public Message updateKeyname(SysKeyname keyname){
        if (StringUtils.isNullOrEmpty(keyname)){
            return Message.nullParamsMessage();
        } else {
            return service.updateKeyname(keyname);
        }
    }


    @ApiOperation(value = "新增键值", notes = "新增键值")
    @PostMapping(value = "/keyvalue")
    public Message saveKeyvalue(SysKeyvalue keyvalue){
        if (StringUtils.isNullOrEmpty(keyvalue)){
            return Message.nullParamsMessage();
        } else {
            return service.saveKeyvalue(keyvalue);
        }
    }

    @ApiOperation(value = "删除键值", notes = "删除键值")
    @DeleteMapping(value = "/keyvalues")
    public Message deleteKeyvalues(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.deleteKeyvalues(ids);
        }
    }

    @ApiOperation(value = "更新键值", notes = "更新键值")
    @PutMapping(value = "/keyvalue")
    public Message updateKeyvalue(SysKeyvalue keyvalue){
        if (StringUtils.isNullOrEmpty(keyvalue)){
            return Message.nullParamsMessage();
        } else {
            return service.updateKeyvalue(keyvalue);
        }
    }

    @ApiOperation(value = "键名树", notes = "键名树")
    @GetMapping(value = "/keynameTree")
    public Message getKeynameTree(){
        return service.getKeynameTree();
    }
}
