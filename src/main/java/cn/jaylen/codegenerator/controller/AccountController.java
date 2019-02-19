package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysAccount;
import cn.jaylen.codegenerator.entity.SysUser;
import cn.jaylen.codegenerator.service.AccountService;
import cn.jaylen.codegenerator.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author ljl
 * @create 2018-07-03 17:26
 * @desc 账户控制层
 **/
@RestController
public class AccountController {
    @Resource
    private AccountService service;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public Message login(String username, String password){
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
            return Message.errorMessage(500, "登录名或者密码为空！");
        } else {
            return service.login(username, password);
        }
    }

    @PutMapping(value = "/loginout")
    public Message loginout(HttpSession session){
        return service.loginout(session);
    }

    /**
     * 获取账户列表
     * @param state ：状态
     * @param searchKey ：查询条件
     * @return
     */
    @GetMapping(value = "/accounts")
    public Message getAccounts(Integer state, String searchKey){
        if (state == null){
            return Message.nullParamsMessage();
        } else {
            return service.getAccounts(state,searchKey);
        }
    }

    /**
     * 获取所有的账户列表
     * @return
     */
    @GetMapping(value = "/allAccounts")
    public Message getAllAccount(){
        return service.getAllAccount();
    }

    /**
     * 根据该角色关联的所有账户信息
     * @param roleId
     * @return
     */
    @GetMapping(value = "/getAccountByRoleId")
    public Message getAccountByRoleId(Long roleId){
        if (roleId == null) {
            return Message.nullParamsMessage();
        } else {
            return service.getAccountByRoleId(roleId);
        }
    }

    /**
     * 删除账户信息及用户信息
     * @param accountIds ：账号id数组
     * @param userIds : 用户id数组
     * @return
     */
    @DeleteMapping(value = "/accounts")
    public Message deleteAccounts(Long[] accountIds, Long[] userIds){
        if (accountIds == null || accountIds.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.deleteAccounts(accountIds, userIds);
        }
    }

    /**
     * 重置密码
     * @param ids ：需要重置的账户id数组
     * @return
     */
    @PutMapping(value = "/resetPassword")
    public Message resetPassword(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return service.updatePassword(ids);
        }
    }

    @PostMapping(value = "/account")
    public  Message saveAccount(SysAccount account, SysUser user, Long roleId){
        if (account == null || user == null) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.saveAccount(account, user, roleId));
        }
    }

    /**
     * 更新账户信息
     * @param account
     * @return
     */
    @PutMapping(value = "/account")
    public Message updateAccount(SysAccount account, SysUser user, Long roleId){
        if (account == null || user == null) {
            return Message.nullParamsMessage();
        } else {
            return service.updateAccount(account, user, roleId);
        }
    }

    /**
     * 修改账户状态
     * @param id
     * @param state
     * @return
     */
    @PutMapping(value = "/changeAccountState")
    public Message updateState(Long id, int state) {
        if (id == 0 || state == 0 ) {
            return Message.nullParamsMessage();
        } else {
            return service.updateState(id,state);
        }
    }

    /**
     * 修改密码
     * @param newPassword
     * @return
     */
    @PutMapping(value = "/changePassword")
    public Message changePassword(String newPassword) {
        if (StringUtils.isNullOrEmpty(newPassword)) {
            return Message.nullParamsMessage();
        } else {
            return service.changePassword(newPassword);
        }
    }
}
