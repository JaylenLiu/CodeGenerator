package cn.jaylen.codegenerator.service;


import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysResource;
import cn.jaylen.codegenerator.entity.example.SysResourceExample;

/**
 * @author ljl
 * @create 2018-07-10 15:47
 * @desc 资源管理逻辑业务层
 **/
public interface ResourceService {
    Message getResources(String searchKey);
    Message getAllResources(SysResourceExample example);
    Message saveResource(SysResource resource);
    Message updateResource(SysResource resource);
    Message deleteResources(Long[] ids);
    Message getResByRoleId(Long roleId);
    Message getResTree();

    Message getResTreeByRoleId(Long roleId);
    Message unassignResTree(Long roleId);
    Message assignRes(String mode, Long roleId, Long[] keys, Long[] harfKeys);
}
