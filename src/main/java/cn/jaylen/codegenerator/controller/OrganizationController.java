package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysOrganization;
import cn.jaylen.codegenerator.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ljl
 * @create 2018-07-09 16:54
 * @desc 组织管理
 **/
@RestController
public class OrganizationController {
    @Resource
    OrganizationService service;

    /**
     * 获取组织架构树
     * @return
     */
    @GetMapping(value = "orgTree")
    public Message getOrganizationTree(){
        return service.getOrganizationTree();
    }

    /**
     * 根据组织类型获取数据
     * @param orgType
     * @return
     */
    @GetMapping(value = "orgByType")
    public Message getOrgByType(Integer orgType, Long parentId){
        if (orgType == null) {
            return Message.nullParamsMessage();
        } else {
            return service.getOrgByType(orgType, parentId);
        }
    }

    /**
     * 保存组织信息
     * @param organization
     * @return
     */
    @PostMapping(value = "/org")
    public Message saveOrg(SysOrganization organization){
        if (organization == null) {
            return Message.nullParamsMessage();
        } else {
            return service.saveOrg(organization);
        }
    }

    /**
     * 删除组织信息
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/orgs")
    public Message deleteOrgs(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            try {
                return service.deleteOrgs(ids);
            } catch (Exception e) {
                return Message.errorMessage(500, "删除失败！");
            }
        }
    }

    /**
     * 更新组织信息
     * @param organization
     * @return
     */
    @PutMapping(value = "/org")
    public Message updateOrg(SysOrganization organization) {
        if (organization == null) {
            return Message.nullParamsMessage();
        } else {
            return service.updateOrg(organization);
        }
    }
}
