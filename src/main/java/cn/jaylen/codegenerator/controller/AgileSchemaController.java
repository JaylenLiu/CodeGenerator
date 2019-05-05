package cn.jaylen.codegenerator.controller;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.AgileSchema;
import cn.jaylen.codegenerator.service.impl.AgileSchemaServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgileSchemaController{
    @Autowired
    AgileSchemaServiceImpl service;

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/agileSchema")
    public Message selectById(Long id){
        return Message.successMessage(service.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @GetMapping("/agileSchemas")
    public Message selectAll(){
        return Message.successMessage(service.selectAll());
    }

    @ApiOperation(value = "获取schema树")
    @GetMapping("/schemaTree")
    public Message selectSchemaTree(){
        return Message.successMessage(service.selectSchemaTree());
    }

    @ApiOperation(value = "新增AgileSchema", notes = "新增AgileSchema")
    @PostMapping("/agileSchema")
    public Message insertAgileSchema(AgileSchema agileSchema){
        agileSchema.preInsert();
        service.insert(agileSchema);
        return Message.successMessage(agileSchema);
    }

    @ApiOperation(value = "删除AgileSchema", notes = "删除AgileSchema")
    @DeleteMapping("/agileSchema")
    public Message deleteAgileSchema(Long id){
        return Message.successMessage(service.deleteAgileSchema(id));
    }

    @ApiOperation(value = "删除多个", notes = "删除多个")
    @DeleteMapping("/agileSchemas")
    public Message deleteByIds(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.deleteByIDs(ids));
        }
    }

    @ApiOperation(value = "更新AgileSchema", notes = "更新AgileSchema")
    @PutMapping(value = "/agileSchema")
    public Message updateAgileSchema(AgileSchema agileSchema){
        agileSchema.preUpdate();
        return Message.successMessage(service.updateByPrimaryKeySelective(agileSchema));
    }
}
