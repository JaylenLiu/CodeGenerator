package cn.jaylen.codegenerator.service.impl;
import cn.jaylen.codegenerator.base.BaseServiceImpl;
import cn.jaylen.codegenerator.dao.SysNoticeMapper;
import cn.jaylen.codegenerator.entity.SysNotice;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.example.SysNoticeExample;
import cn.jaylen.codegenerator.service.SysNoticeService;
import javax.annotation.Resource;

import cn.jaylen.codegenerator.util.SpringContextUtil;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jaylen.codegenerator.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Arrays;
import java.util.List;

@Service
public class SysNoticeServiceImpl extends BaseServiceImpl<SysNotice> implements SysNoticeService {
    @Resource
    SysNoticeMapper sysNoticeMapper;

    private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);

    @Override
    public List<SysNotice> selectAll(SysNoticeExample example){
        return sysNoticeMapper.selectByExample(example);
    }

    @Override
    public Message selectByPages(SysNoticeExample example){
        // 获取分页信息
        PageHelper.startPage(PageUtil.getPageNum(), PageUtil.getPageSize());
        List<SysNotice> result;
        try {
            result = selectAll(example);
        } catch (Exception e) {
            logger.error("获取SysNotice信息失败}！", e);
            return Message.errorMessage(500, "获取SysNotice信息失败！");
        }

        PageInfo pageInfo = new PageInfo(result);
        Message<SysNotice> message = Message.successMessage(pageInfo);
        return message;
    }

    @Override
    public int deleteByIDs(Long[] ids){
        SysNoticeExample example = new SysNoticeExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return sysNoticeMapper.deleteByExample(example);
    }

    @Override
    public Message changeState(Long id, Integer state) {
        SysNotice sysNotice = sysNoticeMapper.selectByPrimaryKey(id);
        sysNotice.setState(state);
        return Message.successMessage(sysNoticeMapper.updateByPrimaryKeySelective(sysNotice));
    }

    @Override
    public Message readAllNotice() {
        SysNoticeExample example = new SysNoticeExample();
        example.createCriteria().andNotifierEqualTo((Long) SpringContextUtil.getSession().getAttribute("accountId")).andStateEqualTo(1);
        List<SysNotice> notices = sysNoticeMapper.selectByExample(example);

        notices.forEach(sysNotice -> {
            sysNotice.setState(2);
            sysNoticeMapper.updateByPrimaryKeySelective(sysNotice);
        });

        return Message.successMessage(1);
    }

    @Override
    public Message deleteAllNotice() {
        SysNoticeExample example = new SysNoticeExample();
        example.createCriteria().andNotifierEqualTo((Long) SpringContextUtil.getSession().getAttribute("accountId")).andStateEqualTo(2);
        List<SysNotice> notices = sysNoticeMapper.selectByExample(example);

        notices.forEach(sysNotice -> {
            sysNotice.setState(3);
            sysNoticeMapper.updateByPrimaryKeySelective(sysNotice);
        });

        return Message.successMessage(1);
    }
}
