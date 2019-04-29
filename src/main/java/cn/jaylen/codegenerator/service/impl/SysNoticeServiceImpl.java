package cn.jaylen.codegenerator.service.impl;
import cn.jaylen.codegenerator.base.BaseServiceImpl;
import cn.jaylen.codegenerator.dao.SysNoticeMapper;
import cn.jaylen.codegenerator.entity.SysNotice;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.example.SysNoticeExample;
import cn.jaylen.codegenerator.service.SysNoticeService;
import javax.annotation.Resource;
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
    public int deleteByIDs(Integer[] ids){
        SysNoticeExample example = new SysNoticeExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return sysNoticeMapper.deleteByExample(example);
    }
}
