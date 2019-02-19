package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysRole;
import cn.jaylen.codegenerator.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ljl
 * @create 2018-07-09 14:38
 * @desc 角色管理
 **/
@RestController
public class RoleController {
    @Resource
    private RoleService service;

    /**
     * 获取角色列表
     * @param roleName ： 角色名称
     * @return
     */
    @GetMapping(value = "/roles")
    public Message getRoles(String roleName){
        return service.getRoles(roleName);
    }

    @GetMapping(value = "/allRoles")
    public Message getAllRoles(){
        return service.getAllRoles();
    }

    /**
     * 更新角色
     * @param role ： 实体
     * @return
     */
    @PutMapping(value = "role")
    public Message updateRoles(SysRole role) {
        if (role == null) {
            return Message.nullParamsMessage();
        } else {
            return service.updateRoles(role);
        }
    }

    /**
     * 删除roles
     * @param ids : 需要删除的id数组
     * @return
     */
    @DeleteMapping(value = "roles")
    public  Message deleteRoles(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.deleteRoles(ids);
        }
    }

    /**
     * 保存role
     * @param role : 实体
     * @return
     */
    @PostMapping(value = "role")
    public Message saveRole(SysRole role) {
        if (role == null) {
            return Message.nullParamsMessage();
        } else {
            return service.saveRole(role);
        }
    }

    /**
     * 根据角色id分配资源
     * @param roleId : 角色id
     * @param resIds : 资源id数组
     * @return
     */
    @PostMapping(value = "/roleRes")
    public Message saveRoleRes(Long roleId, Long[] resIds){
        if (roleId == null || resIds == null || resIds.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.saveRoleRes(roleId, resIds);
        }
    }

    /**
     * 取消资源分配
     * @param roleId ： 角色id
     * @param resIds : 资源id数组
     * @return
     */
    @DeleteMapping(value = "/roleRes")
    public Message deleteRoleRes(Long roleId, Long[] resIds) {
        if (roleId == null || resIds == null || resIds.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.deleteRoleRes(roleId, resIds);
        }
    }

    /**
     * 根据角色id分配账号
     * @param roleId
     * @param accountIds
     * @return
     */
    @PostMapping(value = "/roleAccount")
    public Message saveRoleAccount(Long roleId, Long[] accountIds){
        if (roleId == null || accountIds == null || accountIds.length == 0){
            return Message.nullParamsMessage();
        } else {
            return service.saveRoleAccount(roleId, accountIds);
        }
     }

    /**
     * 取消分配账号
     * @param roleId
     * @param accountIds
     * @return
     */
     @DeleteMapping(value = "/roleAccount")
     public Message deleteRoleAccount(Long roleId, Long[] accountIds){
         if (roleId == null || accountIds == null || accountIds.length == 0){
             return Message.nullParamsMessage();
         } else {
             return service.deleteRoleAccount(roleId, accountIds);
         }
     }
}
