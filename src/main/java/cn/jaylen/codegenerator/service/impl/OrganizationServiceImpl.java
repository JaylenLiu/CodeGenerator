package cn.jaylen.codegenerator.service.impl;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.dao.SysOrganizationMapper;
import cn.jaylen.codegenerator.entity.SysOrganization;
import cn.jaylen.codegenerator.entity.example.SysOrganizationExample;
import cn.jaylen.codegenerator.service.OrganizationService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljl
 * @create 2018-07-09 16:56
 * @desc 组织管理逻辑层
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    SysOrganizationMapper sysOrganizationMapper;

    private Logger logger = (Logger) LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Override
    public Message getOrganizationTree() {
        SysOrganizationExample example = new SysOrganizationExample();
        example.createCriteria().andIdIsNotNull();
        example.setOrderByClause("sort");
        try {
            List<SysOrganization> orgs = sysOrganizationMapper.selectByExample(example);
            if (orgs != null && orgs.size() != 0) {
                return Message.successMessage(getTreeMap(orgs, null));
            } else {
                return Message.errorMessage(500, "没有组织信息！");
            }
        } catch (Exception e) {
            logger.error("获取组织架构树失败！", e);
            return Message.errorMessage(500, "获取组织架构树失败！");
        }
    }

    private List<Map<String, Object>> getTreeMap(List<SysOrganization> list, Long pid) {
        List<Map<String, Object>> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (SysOrganization organization : list) {
                if ((pid == null &&  organization.getParentId() == -1) || organization.getParentId() == pid) {
                    Map<String, Object> temp = new HashMap<>();
                    List<Map<String, Object>> sublist = getTreeMap(list, organization.getId());
                    if (sublist != null && sublist.size() != 0) {
                        temp.put("children", sublist);
                    }
                    temp.put("id", organization.getId());
                    temp.put("parentId", organization.getParentId());
                    temp.put("label", organization.getOrgName());
                    temp.put("orgType", organization.getOrgType());
                    temp.put("sort", organization.getSort());
                    res.add(temp);
                }
            }
        }
        return res;
    }

    @Override
    public Message getOrgByType(Integer orgType, Long parentId) {
        SysOrganizationExample example = new SysOrganizationExample();
        SysOrganizationExample.Criteria criteria = example.createCriteria();
        criteria.andOrgTypeEqualTo(orgType).andParentIdIsNotNull();
        if (parentId != null) {
            criteria.andParentIdEqualTo(parentId);
        }
        try{
            return Message.successMessage(sysOrganizationMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("获取组织信息失败！", e);
            return Message.errorMessage(500, "获取组织信息失败！");
        }
    }

    @Override
    public Message saveOrg(SysOrganization organization) {
        try {
            organization.preInsert();
            return Message.successMessage(sysOrganizationMapper.insertSelective(organization));
        } catch (Exception e) {
            logger.error("保存组织信息失败！", e);
            return Message.errorMessage(500, "保存组织信息失败！" );
        }
    }

    @Override
    @Transactional
    public Message deleteOrgs(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
           deleteOrg(ids[i]);
        }
        return Message.successMessage(ids.length);
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    public void deleteOrg(Long id) {
        SysOrganizationExample example = new SysOrganizationExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<SysOrganization> orgs = sysOrganizationMapper.selectByExample(example);
        if (orgs != null && orgs.size() != 0) {
            for (SysOrganization org :
                    orgs) {
                deleteOrg(org.getId());
            }
        }
        sysOrganizationMapper.deleteByPrimaryKey(id);

    }

    @Override
    public Message updateOrg(SysOrganization organization) {
        try{
            return Message.successMessage(sysOrganizationMapper.updateByPrimaryKeySelective(organization));
        } catch (Exception e) {
            logger.error("更新组织信息失败！", e);
            return Message.errorMessage(500, "更新组织信息失败！");
        }
    }
}
