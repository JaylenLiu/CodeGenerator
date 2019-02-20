package cn.jaylen.codegenerator.service.impl;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.dao.*;
import cn.jaylen.codegenerator.entity.SysAccount;
import cn.jaylen.codegenerator.entity.SysAccountRole;
import cn.jaylen.codegenerator.entity.SysOrganization;
import cn.jaylen.codegenerator.entity.SysUser;
import cn.jaylen.codegenerator.entity.example.SysAccountExample;
import cn.jaylen.codegenerator.entity.example.SysAccountRoleExample;
import cn.jaylen.codegenerator.entity.example.SysUserExample;
import cn.jaylen.codegenerator.service.AccountService;
import cn.jaylen.codegenerator.util.MD5Util;
import cn.jaylen.codegenerator.util.PageUtil;
import cn.jaylen.codegenerator.util.SpringContextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ljl
 * @create 2018-07-03 17:29
 * @desc 账户逻辑层
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private SysAccountMapper sysAccountMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysAccountRoleMapper sysAccountRoleMapper;

    @Autowired
    private SysOrganizationMapper organizationMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    /**
     * 登录
     * @param username ： 用户名
     * @param password ： 密码
     * @return
     */
    public Message login(String username, String password){
        // 取出账户信息
        SysAccountExample example = new SysAccountExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysAccount> list;
        try {
            list = sysAccountMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("获取账户信息失败！", e);
            return Message.errorMessage(500, "获取账户信息失败！");
        }
        // 校验
        if (list == null || list.size() == 0) {
            return Message.errorMessage(500, "用户名不存在！");
        } else if (list.get(0).getState() != 2){
            return Message.errorMessage(500, "账号已被禁用！");
        } else if (list.get(0).getPassword().equals(MD5Util.getMD5_32(password))){

            HttpSession session = SpringContextUtil.getSession();
            session.setAttribute("username", list.get(0).getUsername());
            session.setAttribute("accountId", list.get(0).getId());

            // 设置session 时长,单位为秒，30分钟过期
            session.setMaxInactiveInterval(30 * 60);


            // 获取用户信息
            SysUser user = sysUserMapper.selectByPrimaryKey(list.get(0).getUserId());
            SysOrganization organization = organizationMapper.selectByPrimaryKey(user.getOrgId());
            String rolename = sysRoleMapper.getRoleNameByUsername(username);

            // 将信息返回到前台，
            Map<String, Object> result = new HashMap<>();
            result.put("username",list.get(0).getUsername());
            result.put("realname", user.getRealName());
            result.put("department", organization.getOrgName());
            session.setAttribute("realname", user.getRealName());
            session.setAttribute("rolename", rolename);

//            saveSessionInfo(username);
            // 登录日志
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            logger.info("用户：" + username +"(ip:" + SpringContextUtil.getRequest().getRemoteAddr() + ")于" + format.format(new Date()) + " 登录系统！");

            // 记录登录时间、ip以及将错误次数清零
            SysAccount account = list.get(0);
            account.setLastLoginTime(new Date());
            account.setErrorCount(0);
            account.setLastLoginIp(SpringContextUtil.getRequest().getRemoteAddr());
            sysAccountMapper.updateByPrimaryKeySelective(account);

            return Message.successMessage(result);
        } else {
            // 登录失败将登录错误次数+1
            SysAccount sysAccount = list.get(0);
            sysAccount.setErrorCount(sysAccount.getErrorCount() + 1);
            sysAccountMapper.updateByPrimaryKeySelective(sysAccount);
            return Message.errorMessage(500, "密码错误");
        }
    }

    @Override
    public Message loginout(HttpSession session) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        logger.info("用户：" + session.getAttribute("username") +"(ip:" + SpringContextUtil.getRequest().getRemoteAddr() + ")于" + format.format(new Date()) + " 登录系统！");
//        removeSessionInfo(session.getAttribute("username").toString());

        session.removeAttribute("username");
        session.removeAttribute("accountId");
        session.removeAttribute("realname");

        return Message.successMessage("退出成功！");
    }

    /**
     * 获取账户信息
     * @param state ： 状态，1：禁用；2：正常；2：锁定
     * @param searchKey ：查询条件
     */
    public Message getAccounts(int state, String searchKey){
        // 获取分页信息
        PageHelper.startPage(PageUtil.getPageNum(), PageUtil.getPageSize());
        List<Map<String, Object>> result;
        try {
            result = sysAccountMapper.selectAccountLists(state,searchKey);
        } catch (Exception e) {
            logger.error("获取账号信息失败！", e);
            return Message.errorMessage(500, "获取账号信息失败！");
        }

        PageInfo pageInfo = new PageInfo(result);
        Message<SysAccount> message = Message.successMessage(result);
        message.setPages(pageInfo.getPages());
        return message;
    }

    @Override
    public Message getAllAccount() {
        SysAccountExample example = new SysAccountExample();
        example.createCriteria().andIdIsNotNull();
        try{
            return Message.successMessage(sysAccountMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("获取账号信息失败！", e);
            return Message.errorMessage(500, "获取账号信息失败！");
        }
    }

    @Override
    public Message getAccountByRoleId(Long roleId) {
        SysAccountRoleExample example = new SysAccountRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysAccountRole> lists;
        try{
            lists = sysAccountRoleMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("获取账号信息失败！", e);
            return Message.errorMessage(500, "获取账号信息失败！");
        }

        if (lists == null || lists.size() == 0) {
            return Message.errorMessage(500, "该角色未分配账号");
        } else {
            List<Long> result = lists.stream().map(accountRole ->
                    accountRole.getAccountId()
            ).collect(Collectors.toList());
            return Message.successMessage(result);
        }
    }


    @Override
    @Transactional
    public Message saveAccount(SysAccount account, SysUser user, Long roleId){
        try{
            // 保存用户信息
            user.preInsert();
            sysUserMapper.insertSelective(user);


            // 保存账号信息
            account.setUserId(user.getId()); // mybatis 自动回填id
            account.preInsert();
            // md5加密,默认密码123456
            account.setPassword(MD5Util.getMD5_32("123456"));
            sysAccountMapper.insert(account);

            // 保存账户角色关联信息
            SysAccountRole accountRole = new SysAccountRole();
            accountRole.setAccountId(account.getId());
            accountRole.setRoleId(roleId);
            sysAccountRoleMapper.insertSelective(accountRole);

            return Message.successMessage(1);
        } catch (Exception e) {
            logger.error("保存账号信息失败！", e);
            return Message.errorMessage(500, "保存账号信息失败！");
        }
    }


    /**
     * 更新账户类
     * @param account: 账户实体
     */
    public Message updateAccount(SysAccount account,SysUser user, Long roleId){
        try{
            user.setId(account.getUserId());
            user.preUpdate();
            sysUserMapper.updateByPrimaryKeySelective(user);

            account.preUpdate();
            sysAccountMapper.updateByPrimaryKeySelective(account);

            SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
            accountRoleExample.createCriteria().andAccountIdEqualTo(account.getId());
            List<SysAccountRole> accountRoleList = sysAccountRoleMapper.selectByExample(accountRoleExample);
            if (accountRoleList == null || accountRoleList.size() == 0) {
                SysAccountRole accountRole = new SysAccountRole();
                accountRole.setAccountId(account.getId());
                accountRole.setRoleId(roleId);
                sysAccountRoleMapper.insert(accountRole);
            } else {
                SysAccountRole accountRole = accountRoleList.get(0);
                accountRole.setRoleId(roleId);
                sysAccountRoleMapper.updateByPrimaryKey(accountRole);
            }
            return Message.successMessage(1);
        } catch (Exception e) {
            logger.error("更新账号信息失败！", e);
            return Message.errorMessage(500, "更新账号信息失败！");
        }
    }

    @Override
    public Message updateState(Long id, int state) {
        SysAccount account = sysAccountMapper.selectByPrimaryKey(id);
        if (account == null) {
            return Message.errorMessage(500, "该账户不存在！");
        } else {
            account.preUpdate();
            account.setState(state);
            sysAccountMapper.updateByPrimaryKeySelective(account);
            return Message.successMessage(1);
        }
    }

    @Override
    public Message changePassword(String newPassword) {
        Long accountId = Long.parseLong(SpringContextUtil.getRequest().getSession().getAttribute("accountId").toString());

        SysAccount account = sysAccountMapper.selectByPrimaryKey(accountId);
        account.setPassword(MD5Util.getMD5_32(newPassword));
//        removeSessionInfo(account.getUsername());
        return Message.successMessage(sysAccountMapper.updateByPrimaryKeySelective(account));
    }

    /**
     * 删除账户信息及用户信息
     * @param accountIds ：账号id数组
     * @param userIds : 用户id数组
     * @return
     */
    @Override
    public Message deleteAccounts(Long[] accountIds, Long[] userIds){
        // 账号删除条件构建
        SysAccountExample accountExample = new SysAccountExample();
        accountExample.createCriteria().andIdIn(Arrays.asList(accountIds));

        // 用户删除条件构建
        if (userIds != null &&  userIds.length != 0) {
            SysUserExample userExample = new SysUserExample();
            userExample.createCriteria().andIdIn(Arrays.asList(userIds));
            sysUserMapper.deleteByExample(userExample);
        }


        // 删除账号与角色的关联信息
        SysAccountRoleExample accountRoleExample = new SysAccountRoleExample();
        accountRoleExample.createCriteria().andAccountIdIn(Arrays.asList(accountIds));
        try{
            sysAccountMapper.deleteByExample(accountExample);
            sysAccountRoleMapper.deleteByExample(accountRoleExample);
            return Message.successMessage(accountIds.length);
        }catch (Exception e) {
            logger.error("删除账号信息失败！", e);
            return Message.errorMessage(500, "删除账号信息失败！");
        }
    }

    /**
     * 重置密码
     * @param ids
     * @return
     */
    @Override
    public Message updatePassword(Long[] ids) {
        // 默认密码
        String defaultPassword = MD5Util.getMD5_32("123456");
        try{
            for (int i = 0; i < ids.length; i++) {
                SysAccount account = sysAccountMapper.selectByPrimaryKey(ids[i]);
                account.setPassword(defaultPassword);
                sysAccountMapper.updateByPrimaryKeySelective(account);
//                removeSessionInfo(account.getUsername());
            }
            return Message.successMessage(ids.length);
        }catch (Exception e) {
            logger.error("获取账号信息失败！", e);
            return Message.errorMessage(500, "获取账号信息失败！");
        }
    }

    /**
     * 在servletContext中移除session信息
     */
    private void removeSessionInfo(String username){
        HttpSession session = SpringContextUtil.getRequest().getSession();
        ServletContext application= session.getServletContext();
        Map<String, HashSet> loginMap = (Map<String, HashSet>)application.getAttribute("loginMap");
        if (loginMap.containsKey(username)) {
            loginMap.remove(username);
        }
    }

    /**
     * 在servletContext中存放所有用户session信息
     */
    private synchronized void saveSessionInfo(String username){
        HttpSession session = SpringContextUtil.getSession();
        ServletContext application= session.getServletContext();
        Map<String, HashSet> loginMap = (Map<String, HashSet>)application.getAttribute("loginMap");
        if(loginMap==null){
            loginMap = new HashMap<>();
            // 存入新信息
            HashSet set = new HashSet();
            set.add(session.getId() + ":" + System.currentTimeMillis());
            loginMap.put(username, set);
            application.setAttribute("loginMap", loginMap);
        } else {
            // 消除失效的session信息
            if (loginMap.containsKey(username)){
                loginMap.get(username).add(session.getId() + ":" + System.currentTimeMillis());
                HashSet<String> loginSet = loginMap.get(username);
                Iterator<String> iterator = loginSet.iterator();
                while(iterator.hasNext()){
                    String loginInfo = iterator.next();
                    Long loginTime = Long.parseLong(loginInfo.split(":")[1]);
                    if ((System.currentTimeMillis() - loginTime) > 30 * 60 * 1000) {
                        iterator.remove();
//                        loginSet.remove(loginInfo);
                    }
                }
            } else {
                HashSet set = new HashSet();
                set.add(session.getId() + ":" + System.currentTimeMillis());
                loginMap.put(username, set);
            }
        }
    }
}
