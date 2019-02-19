package cn.jaylen.codegenerator.controller;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.AgileComponent;
import cn.jaylen.codegenerator.service.AgileComponentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgileComponentController{
    @Autowired
    AgileComponentService service;

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/agileComponent")
    public Message selectById(Long id){
        return Message.successMessage(service.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "根据实体id获取component")
    @GetMapping(value = "/agileComponentByName")
    public Message selectByEntityName(Long entityId, String fieldName){
        if (entityId == null) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.selectByEntityName(entityId, fieldName));
        }
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @GetMapping("/agileComponents")
    public Message selectAll(){
        return Message.successMessage(service.selectAll());
    }

    @ApiOperation(value = "新增AgileComponent", notes = "新增AgileComponent")
    @PostMapping("/agileComponent")
    public Message insertAgileComponent(AgileComponent agileComponent){
        return Message.successMessage(service.insert(agileComponent));
    }

    @ApiOperation(value = "删除AgileComponent", notes = "删除AgileComponent")
    @DeleteMapping("/agileComponent")
    public Message deleteAgileComponent(Long id){
        return Message.successMessage(service.deleteByPrimaryKey(id));
    }

    @ApiOperation(value = "删除多个", notes = "删除多个")
    @DeleteMapping("/agileComponents")
    public Message deleteByIds(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.deleteByIDs(ids));
        }
    }

    @ApiOperation(value = "更新AgileComponent", notes = "更新AgileComponent")
    @PutMapping(value = "/agileComponent")
    public Message updateAgileComponent(AgileComponent agileComponent){
        return Message.successMessage(service.updateByPrimaryKey(agileComponent));
    }
}
