package cn.jaylen.codegenerator.service;



import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysAccount;
import cn.jaylen.codegenerator.entity.SysUser;

import javax.servlet.http.HttpSession;

/**
 * @author ljl
 * @create 2018-07-03 17:28
 * @desc 账户管理抽象逻辑层
 **/
public interface AccountService {
    Message login(String username, String password);
    Message loginout(HttpSession session);
    Message getAccounts(int state, String username);
    Message getAllAccount();
    Message getAccountByRoleId(Long roleId);
    Message deleteAccounts(Long[] accountIds, Long[] userIds);
    Message updatePassword(Long[] ids);
    Message saveAccount(SysAccount account, SysUser user, Long roleId);
    Message updateAccount(SysAccount account, SysUser user, Long roleId);
    Message updateState(Long id, int state);
    Message changePassword(String newPassword);
}
