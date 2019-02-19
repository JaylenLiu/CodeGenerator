package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysResource;
import cn.jaylen.codegenerator.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ljl
 * @create 2018-07-10 15:47
 * @desc 资源管理
 **/
@RestController
public class ResourceController {

    @Resource(name = "resourceServiceImpl")
    ResourceService service;

    /**
     * 获取资源列表
     * @param searchKey ： 查询关键字
     * @return
     */
    @GetMapping(value = "/resources")
    public Message getResources(String searchKey) {
        return service.getResources(searchKey);
    }

    /**
     * 获取所有的资源信息
     * @return
     */
    @GetMapping(value = "/allRes")
    public Message getAllResources(){
        return service.getAllResources();
    }

    /**
     * 通过roleId 获取资源id数组
     * @param roleId
     * @return
     */
    @GetMapping(value = "/getResByRoleId")
    public Message getResByRoleId(Long roleId){
        if (roleId == null ){
            return Message.nullParamsMessage();
        } else {
            return service.getResByRoleId(roleId);
        }
    }

    /**
     * 保存资源实体
     * @param resource
     * @return
     */
    @PostMapping(value = "/resource")
    public Message saveResource(SysResource resource){
        if (resource == null) {
            return Message.nullParamsMessage();
        } else {
            return service.saveResource(resource);
        }
    }

    /**
     * 更新资源实体
     * @param resource
     * @return
     */
    @PutMapping(value = "/resource")
    public Message updateResource(SysResource resource) {
        if (resource == null) {
            return Message.nullParamsMessage();
        } else {
            return service.updateResource(resource);
        }
    }

    /**
     * 删除多个资源实体
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/resources")
    public Message deleteResources(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.deleteResources(ids);
        }
    }

    /**
     * 获取资源树
     * @return
     */
    @GetMapping(value = "/resTree")
    public Message getResTree(){
        return service.getResTree();
    }


}
