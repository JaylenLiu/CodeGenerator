package cn.jaylen.codegenerator.service.impl;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.dao.SysResourceMapper;
import cn.jaylen.codegenerator.dao.SysRoleResMapper;
import cn.jaylen.codegenerator.entity.SysResource;
import cn.jaylen.codegenerator.entity.SysRoleRes;
import cn.jaylen.codegenerator.entity.example.SysResourceExample;
import cn.jaylen.codegenerator.entity.example.SysRoleResExample;
import cn.jaylen.codegenerator.service.ResourceService;
import cn.jaylen.codegenerator.util.PageUtil;
import cn.jaylen.codegenerator.util.SpringContextUtil;
import cn.jaylen.codegenerator.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ljl
 * @create 2018-07-10 15:48
 * @desc 资源管理业务层
 **/
@Service
public class ResourceServiceImpl implements ResourceService {
    @Resource
    SysResourceMapper sysResourceMapper;

    @Resource
    SysRoleResMapper sysRoleResMapper;

    private Logger logger = (Logger) LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Override
    public Message getResources(String searchKey) {
        // 获取分页信息
        PageHelper.startPage(PageUtil.getPageNum(), PageUtil.getPageSize());

        SysResourceExample example = new SysResourceExample();
        example.setOrderByClause("create_time desc, update_time desc");
        if (!StringUtils.isNullOrEmpty(searchKey)) {
            example.createCriteria().andResNameLike("%" + searchKey + "%");
        } else {
            example.createCriteria().andIdIsNotNull();
        }

        List<SysResource> result;
        try{
           result = sysResourceMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("获取资源列表失败！", e);
            return Message.errorMessage(500, "获取资源列表失败！");
        }

        // 封装分页信息
        PageInfo pageInfo = new PageInfo(result);
        Message<SysResource> message = Message.successMessage(result);
        message.setPages(pageInfo.getPages());
        return message;
    }

    @Override
    public Message getAllResources( SysResourceExample example) {
        try {
            return Message.successMessage(sysResourceMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("获取资源数据失败！", e);
            return Message.errorMessage(500, "获取资源数据失败！");
        }
    }

    @Override
    public Message saveResource(SysResource resource) {
        try{
            resource.preInsert();
            return Message.successMessage(sysResourceMapper.insertSelective(resource));
        } catch (Exception e) {
            logger.error("保存资源信息失败！", e);
            return Message.errorMessage(500, "保存资源信息失败！");
        }
    }

    @Override
    public Message updateResource(SysResource resource) {
        try{
            resource.preUpdate();
            return Message.successMessage(sysResourceMapper.updateByPrimaryKeySelective(resource));
        } catch (Exception e) {
            logger.error("更新资源信息失败！", e);
            return Message.errorMessage(500, "更新资源信息失败！");
        }
    }

    @Override
    public Message deleteResources(Long[] ids) {
        // 删除资源
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        // 删除资源与角色的关联信息
        SysRoleResExample roleResExample = new SysRoleResExample();
        roleResExample.createCriteria().andResIdIn(Arrays.asList(ids));
        try{
            sysResourceMapper.deleteByExample(example);
            sysRoleResMapper.deleteByExample(roleResExample);
            return Message.successMessage(ids.length);
        } catch (Exception e) {
            logger.error("删除资源信息失败！", e);
            return Message.errorMessage(500, "删除资源信息失败！");
        }
    }

    /**
     * 通过角色Id获取资源id数组
     * @param roleId
     * @return
     */
    @Override
    public Message getResByRoleId(Long roleId) {
        SysRoleResExample example = new SysRoleResExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRoleRes> lists = sysRoleResMapper.selectByExample(example);
        if (lists == null || lists.size() == 0) {
            return Message.errorMessage(500, "该角色没有分配资源");
        } else {
            List<Long> result = lists.stream().map(res ->
                 res.getResId()
            ).collect(Collectors.toList());
            return Message.successMessage(result);
        }
    }

    /**
     * 获取资源树
     * @return
     */
    public Message getResTree(){
        Long accountId = (Long) SpringContextUtil.getSession().getAttribute("accountId");
        try {
            List<SysResource> lists = sysResourceMapper.selectByAccountId(accountId);
            return Message.successMessage(getTreeMap(lists, null));
        } catch (Exception e){
            logger.error("获取资源树失败！", e);
            return Message.errorMessage(500,"获取资源树失败！" );
        }
    }

    private List<Map<String, Object>> getTreeMap(List<SysResource> list, Long pid) {
        List<Map<String, Object>> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (SysResource resource : list) {
                if ((pid == null &&  resource.getParentId() == null) || resource.getParentId() == pid) {
                    Map<String, Object> temp = new HashMap<>();
                    List<Map<String, Object>> sublist = getTreeMap(list, resource.getId());
                    if (sublist != null && sublist.size() != 0) {
                        temp.put("subs", sublist);
                    }
                    temp.put("icon", resource.getIcon());
                    temp.put("index", resource.getResPath());
                    temp.put("title", resource.getResName());
                    res.add(temp);
                }
            }
        }
        return res;
    }

    @Override
    public Message getResTreeByRoleId(Long roleId) {
        Message message = getResByRoleId(roleId);
        if (message.getHttpCode() != 200) {
            return message;
        }
        List<Long> resIds = (List<Long>) message.getData();
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andIdIn(resIds);
        List<SysResource> resources = sysResourceMapper.selectByExample(example);
        return Message.successMessage(getResTree(resources, null));
    }

    private List<Map<String,Object>> getResTree(List<SysResource> list, Long pid){
        List<Map<String, Object>> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (SysResource resource : list) {
                if ((pid == null &&  resource.getParentId() == null) || resource.getParentId() == pid) {
                    Map<String, Object> temp = new HashMap<>();
                    List<Map<String,Object>> sublist = getResTree(list, resource.getId());
                    if (sublist != null && sublist.size() != 0) {
                        temp.put("children", sublist);
                    }
                    temp.put("id", resource.getId());
                    temp.put("pid", resource.getParentId());
                    temp.put("label", resource.getResName());
                    res.add(temp);
                }
            }
        }
        return res;
    }

    @Override
    public Message assignRes(String mode, Long roleId, Long[] keys, Long[] harfKeys) {
        if (mode.equals("add")) {
            if (harfKeys != null) {
                assignParentMenu(roleId, harfKeys);
            }
            for (int i = 0; i < keys.length; i++) {
                SysRoleRes roleRes = new SysRoleRes();
                roleRes.setResId(keys[i]);
                roleRes.setRoleId(roleId);
                sysRoleResMapper.insertSelective(roleRes);
            }
        } else if (mode.equals("remove")){
            SysRoleResExample roleResExample = new SysRoleResExample();
            roleResExample.createCriteria().andRoleIdEqualTo(roleId).andResIdIn(Arrays.asList(keys));
            sysRoleResMapper.deleteByExample(roleResExample);
        }
        return Message.successMessage(1);
    }

    /**
     * 父目录分配
     * @param roleId
     * @param resIds
     */
    private void assignParentMenu(Long roleId, Long[] resIds){
        for (int i = 0; i < resIds.length; i++) {
            if (!isAssigned(roleId, resIds[i])){
                SysRoleRes roleRes = new SysRoleRes();
                roleRes.setResId(resIds[i]);
                roleRes.setRoleId(roleId);
                sysRoleResMapper.insertSelective(roleRes);
            }
        }
    }

    /**
     * 验证资源是否已分配
     * @param roleId
     * @param resId
     * @return
     */
    private boolean isAssigned(Long roleId, Long resId){
        SysRoleResExample example = new SysRoleResExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andResIdEqualTo(resId);
        List<SysRoleRes> roleRes = sysRoleResMapper.selectByExample(example);
        return roleRes.size() > 0;
    }

    @Override
    public Message unassignResTree(Long roleId) {
        // 已分配id
        Message message = getResByRoleId(roleId);
        List<Long> resIds = new ArrayList<>();
        if (message.getHttpCode() == 200) {
            resIds = (List<Long>) message.getData();
        }

        // 一级目录
        List<Long> parentIds = sysResourceMapper.getParentDirIds();

        // 所有叶子节点
        if (parentIds != null) {
            resIds.removeAll(parentIds);
        }

        SysResourceExample example = new SysResourceExample();
        if (resIds != null && resIds.size() != 0) {
            example.createCriteria().andIdNotIn(resIds);
        }
        List<SysResource> resources = sysResourceMapper.selectByExample(example);
        List<Map<String,Object>> result =  getResTree(resources, null);
        for (int i = 0; i < result.size(); i++) {
            if (pidIsExist(parentIds, (Long) result.get(i).get("id"))){
                List<Map<String,Object>> temp = (List<Map<String, Object>>) result.get(i).get("children");
                if (temp == null) {
                    result.remove(i--);
                }
            }
        }
        return Message.successMessage(result);
    }

    private boolean pidIsExist(List<Long> parentIds, Long id){
        if (parentIds == null) {
            return false;
        }
        for (int i = 0; i < parentIds.size(); i++) {
            if (parentIds.get(i) == id) {
                return true;
            }
        }
        return false;
    }
}
