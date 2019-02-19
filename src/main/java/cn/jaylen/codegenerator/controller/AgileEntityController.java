package cn.jaylen.codegenerator.controller;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.AgileEntity;
import cn.jaylen.codegenerator.service.impl.AgileEntityServiceImpl;
import cn.jaylen.codegenerator.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgileEntityController{
    @Autowired
    AgileEntityServiceImpl service;

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/agileEntity")
    public Message selectById(Long id){
        return Message.successMessage(service.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @GetMapping("/agileEntitys")
    public Message selectAll(){
        return Message.successMessage(service.selectAll());
    }

    @ApiOperation(value = "根据schemaid获取实体信息")
    @GetMapping("/agileEntityBySchemaId")
    public Message selectBySchemaId(Long schemaId){
        if (schemaId == null) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.selectBySchemaId(schemaId));
        }
    }

    @ApiOperation(value = "新增AgileEntity", notes = "新增AgileEntity")
    @PostMapping("/agileEntity")
    public Message insertAgileEntity(AgileEntity agileEntity){
        return Message.successMessage(service.insert(agileEntity));
    }

    @ApiOperation(value = "删除AgileEntity", notes = "删除AgileEntity")
    @DeleteMapping("/agileEntity")
    public Message deleteAgileEntity(Long id){
        return Message.successMessage(service.deleteByPrimaryKey(id));
    }

    @ApiOperation(value = "删除多个", notes = "删除多个")
    @DeleteMapping("/agileEntitys")
    public Message deleteByIds(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.deleteByIDs(ids));
        }
    }

    @ApiOperation(value = "更新AgileEntity", notes = "更新AgileEntity")
    @PutMapping(value = "/agileEntity")
    public Message updateAgileEntity(AgileEntity agileEntity){
        return Message.successMessage(service.updateByPrimaryKey(agileEntity));
    }

    @ApiOperation(value = "保存AgileEntities信息")
    @PostMapping(value = "/agileEntites")
    public Message saveAgileEntites(Long schemaId, Long connId, String databaseName, String[] tableNames){
        if (schemaId == null || connId == null || StringUtils.isNullOrEmpty(databaseName) || tableNames.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.saveAgileEntites(schemaId,connId,databaseName,tableNames));
        }
    }

    @ApiOperation(value = "获取实体树")
    @GetMapping(value = "/entityTree")
    public Message getEntityTree(Long schemaId){
        if (schemaId == null) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.getEntityTree(schemaId));
        }
    }
}
