package cn.jaylen.codegenerator.service;


import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysRole;

/**
 * @author ljl
 * @create 2018-07-09 14:39
 * @desc 角色管理
 **/
public interface RoleService {
    Message getRoles(String roleName);
    Message getAllRoles();
    Message updateRoles(SysRole role);
    Message deleteRoles(Long[] ids);
    Message saveRole(SysRole role);
    Message saveRoleRes(Long roleId, Long[] resIds);
    Message deleteRoleRes(Long roleId, Long[] resIds);
    Message saveRoleAccount(Long roleId, Long[] accountIds);
    Message deleteRoleAccount(Long roleId, Long[] accountIds);
}
