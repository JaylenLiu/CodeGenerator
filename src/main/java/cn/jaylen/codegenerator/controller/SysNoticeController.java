package cn.jaylen.codegenerator.controller;
import cn.jaylen.codegenerator.entity.SysNotice;
import cn.jaylen.codegenerator.entity.example.SysNoticeExample;
import cn.jaylen.codegenerator.service.SysNoticeService;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.util.SpringContextUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class SysNoticeController{
    @Autowired
    SysNoticeService service;

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "/sysNotice")
    public Message selectById(Long id){
        return Message.successMessage(service.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @GetMapping("/sysNotices")
    public Message selectAll(){
        SysNoticeExample example = new SysNoticeExample();
        return Message.successMessage(service.selectAll(example));
    }

    @ApiOperation(value = "分页获取数据", notes = "分页获取数据")
    @GetMapping("/sysNoticesPages")
    public Message selectByPages(){
        SysNoticeExample example = new SysNoticeExample();
        return service.selectByPages(example);
    }

    @ApiOperation(value = "新增SysNotice", notes = "新增SysNotice")
    @PostMapping("/sysNotice")
    public Message insertSysNotice(SysNotice sysNotice){
        sysNotice.preInsert();
        return Message.successMessage(service.insert(sysNotice));
    }

    @ApiOperation(value = "删除SysNotice", notes = "删除SysNotice")
    @DeleteMapping("/sysNotice")
    public Message deleteSysNotice(Long id){
        return Message.successMessage(service.deleteByPrimaryKey(id));
    }

    @ApiOperation(value = "删除多个", notes = "删除多个")
    @DeleteMapping("/sysNotices")
    public Message deleteByIds(Long[] ids){
        if (ids == null || ids.length == 0) {
            return Message.nullParamsMessage();
        } else {
            return Message.successMessage(service.deleteByIDs(ids));
        }
    }

    @ApiOperation(value = "更新SysNotice", notes = "更新SysNotice")
    @PutMapping(value = "/sysNotice")
    public Message updateSysNotice(SysNotice sysNotice){
        sysNotice.preUpdate();
        return Message.successMessage(service.updateByPrimaryKeySelective(sysNotice));
    }

    @ApiOperation(value = "按状态获取通知信息,1:未读，2:已读")
    @GetMapping("/sysNotice/state/{state}")
    public Message selectByState(@PathVariable(value = "state") Integer state){
        SysNoticeExample example = new SysNoticeExample();
        System.out.println(SpringContextUtil.getSession().getAttribute("accountId"));
        HttpSession session = SpringContextUtil.getSession();
        example.createCriteria().andStateEqualTo(state).andNotifierEqualTo((Long) SpringContextUtil.getSession().getAttribute("accountId"));
        return Message.successMessage(service.selectAll(example));
    }

    @ApiOperation(value = "修改通知状态")
    @PutMapping("/sysNotice/state")
    public Message changeState(Long id, Integer state){
        return service.changeState(id, state);
    }

    @ApiOperation(value = "将全部通知标记为已读")
    @PutMapping("/sysNotice/readAll")
    public Message readAllNotice(){
        return service.readAllNotice();
    }

    @ApiOperation(value = "将全部通知标记为删除状态")
    @PutMapping("/sysNotice/deleteAll")
    public Message deleteAllNotice(){
        return service.deleteAllNotice();
    }
}
