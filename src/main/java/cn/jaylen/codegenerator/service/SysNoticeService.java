package cn.jaylen.codegenerator.service;
import cn.jaylen.codegenerator.base.BaseService;
import cn.jaylen.codegenerator.entity.SysNotice;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.example.SysNoticeExample;

import java.util.List;

public interface SysNoticeService extends BaseService<SysNotice>{
    List<SysNotice> selectAll(SysNoticeExample example);
    Message selectByPages(SysNoticeExample example);
    int deleteByIDs(Long[] ids);
    Message changeState(Long id, Integer state);
    Message readAllNotice();
    Message deleteAllNotice();
}
