package cn.jaylen.codegenerator.service.impl;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.dao.SysAccountRoleMapper;
import cn.jaylen.codegenerator.dao.SysRoleMapper;
import cn.jaylen.codegenerator.dao.SysRoleResMapper;
import cn.jaylen.codegenerator.entity.SysAccountRole;
import cn.jaylen.codegenerator.entity.SysRole;
import cn.jaylen.codegenerator.entity.SysRoleRes;
import cn.jaylen.codegenerator.entity.example.SysAccountRoleExample;
import cn.jaylen.codegenerator.entity.example.SysRoleExample;
import cn.jaylen.codegenerator.entity.example.SysRoleResExample;
import cn.jaylen.codegenerator.service.RoleService;
import cn.jaylen.codegenerator.util.PageUtil;
import cn.jaylen.codegenerator.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ljl
 * @create 2018-07-09 14:39
 * @desc 角色管理
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    SysRoleResMapper sysRoleResMapper;

    @Resource
    SysAccountRoleMapper sysAccountRoleMapper;

    private Logger logger = (Logger) LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public Message getRoles(String roleName) {
        // 获取分页信息
        PageHelper.startPage(PageUtil.getPageNum(), PageUtil.getPageSize());

        SysRoleExample example = new SysRoleExample();
        if (!StringUtils.isNullOrEmpty(roleName)) {
            example.createCriteria().andRoleNameLike("%" + roleName + "%");
        }
        List<SysRole> result;
        try{
            result = sysRoleMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("获取角色列表失败！", e);
            return Message.errorMessage(500, "获取角色列表失败！");
        }

        // 封装分页信息
        PageInfo pageInfo = new PageInfo(result);
        Message<SysRole> message = Message.successMessage(result);
        message.setPages(pageInfo.getPages());
        return message;
    }

    @Override
    public Message getAllRoles() {
        try{
            return Message.successMessage(sysRoleMapper.selectByExample(null));
        } catch (Exception e) {
            logger.error("获取角色列表失败！", e);
            return Message.errorMessage(500, "获取角色列表失败！");
        }
    }

    @Override
    public Message updateRoles(SysRole role) {
        try{
            role.preUpdate();
            return Message.successMessage(sysRoleMapper.updateByPrimaryKeySelective(role));
        } catch (Exception e) {
            logger.error("更新角色失败！", e);
            return Message.errorMessage(500, "更新角色失败！");
        }
    }

    @Override
    public Message deleteRoles(Long[] ids) {
        // 删除角色信息
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));

        // 删除角色-资源关联信息
        SysRoleResExample roleResExample = new SysRoleResExample();
        roleResExample.createCriteria().andRoleIdIn(Arrays.asList(ids));

        // 删除角色-账号关联信息
        SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
        accountRoleExample.createCriteria().andRoleIdIn(Arrays.asList(ids));
        try{
            sysRoleMapper.deleteByExample(example);
            sysRoleResMapper.deleteByExample(roleResExample);
            sysAccountRoleMapper.deleteByExample(accountRoleExample);
            return Message.successMessage(ids.length);
        }catch (Exception e) {
            logger.error("删除角色失败！", e);
            return Message.errorMessage(500, "删除角色失败！");
        }
    }

    @Override
    public Message saveRole(SysRole role) {
        try{
            role.preInsert();
            return Message.successMessage(sysRoleMapper.insertSelective(role));
        } catch (Exception e) {
            logger.error("保存角色失败！");
            return Message.errorMessage(500, "保存角色失败！");
        }
    }

    @Override
    @Transactional
    public Message saveRoleRes(Long roleId, Long[] resIds) {
        try{
            for (Long resId :
                    resIds) {
                SysRoleRes roleRes = new SysRoleRes();
                roleRes.setRoleId(roleId);
                roleRes.setResId(resId);
                sysRoleResMapper.insert(roleRes);
            }
            return Message.successMessage(resIds.length);
        } catch (Exception e) {
            logger.error("资源分配失败！", e);
            return Message.errorMessage(500, "资源分配失败！");
        }
    }

    @Override
    public Message deleteRoleRes(Long roleId, Long[] resIds) {
        SysRoleResExample example = new SysRoleResExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andResIdIn(Arrays.asList(resIds));
        try{
            return Message.successMessage(sysRoleResMapper.deleteByExample(example));
        } catch (Exception e) {
            logger.error("取消资源分配失败！", e);
            return Message.errorMessage(500, "取消资源分配失败！");
        }
    }

    @Override
    public Message saveRoleAccount(Long roleId, Long[] accountIds) {
        try{
            for (Long accountId :
                    accountIds) {
                SysAccountRole sysAccountRole = new SysAccountRole();
                sysAccountRole.setRoleId(roleId);
                sysAccountRole.setAccountId(accountId);
                sysAccountRoleMapper.insert(sysAccountRole);
            }
            return Message.successMessage(accountIds.length);
        } catch (Exception e) {
            logger.error("账号分配失败！", e);
            return Message.errorMessage(500, "账号分配失败！");
        }
    }

    @Override
    public Message deleteRoleAccount(Long roleId, Long[] accountIds) {
        SysAccountRoleExample example = new SysAccountRoleExample();

        example.createCriteria().andRoleIdEqualTo(roleId).andAccountIdIn(Arrays.asList(accountIds));
        try{
            return Message.successMessage(sysAccountRoleMapper.deleteByExample(example));
        } catch (Exception e) {
            logger.error("取消账号分配失败！", e);
            return Message.errorMessage(500, "取消账号分配失败！");
        }
    }
}
