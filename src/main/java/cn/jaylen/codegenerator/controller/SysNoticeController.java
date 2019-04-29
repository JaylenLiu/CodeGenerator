package cn.jaylen.codegenerator.controller;
import cn.jaylen.codegenerator.entity.SysNotice;
import cn.jaylen.codegenerator.entity.example.SysNoticeExample;
import cn.jaylen.codegenerator.service.SysNoticeService;
import cn.jaylen.codegenerator.common.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
