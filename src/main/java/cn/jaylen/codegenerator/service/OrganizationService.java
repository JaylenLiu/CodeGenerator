package cn.jaylen.codegenerator.service;


import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysOrganization;

/**
 * @author ljl
 * @create 2018-07-09 16:55
 * @desc 组织管理
 **/
public interface OrganizationService {
    Message getOrganizationTree();
    Message getOrgByType(Integer orgType, Long parentId);
    Message saveOrg(SysOrganization organization);
    Message deleteOrgs(Long[] ids);
    Message updateOrg(SysOrganization organization);
}
