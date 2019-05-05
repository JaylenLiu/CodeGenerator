package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysResource;
import cn.jaylen.codegenerator.entity.example.SysResourceExample;
import cn.jaylen.codegenerator.service.ResourceService;
import io.swagger.annotations.ApiOperation;
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
        SysResourceExample example = new SysResourceExample();
        return service.getAllResources(example);
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


    @ApiOperation(value = "获取资源父目录", notes = "获取资源父目录")
    @GetMapping(value = "/sysResource/parentDir")
    public Message getParentDir(){
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andParentIdIsNull();
        return service.getAllResources(example);
    }

    /**
     * 获取所有资源的树形结构,用于资源分配穿梭框
     * @return
     */
    @GetMapping("/unassignResTree")
    public Message unassignResTree(Long roleId){
        return service.unassignResTree(roleId);
    }

    /**
     * 通过roleId获取菜单树形结构，用于权限分配中的已分配菜单
     * @param roleId
     * @return
     */
    @GetMapping("/getResTreeByRoleId")
    public Message getResTreeByRoleId(Long roleId){
        if (roleId == null ){
            return Message.nullParamsMessage();
        } else {
            return service.getResTreeByRoleId(roleId);
        }
    }

    /**
     * 权限分配
     * @param mode ： add / remove
     * @param roleId
     * @param keys : 移动的id
     * @param harfKeys : 父级id
     * @return
     */
    @PostMapping("/assignRes")
    public Message assignRes(String mode, Long roleId, Long[] keys, Long[] harfKeys){
        return service.assignRes(mode, roleId, keys, harfKeys);
    }
}
