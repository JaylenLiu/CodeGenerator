package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.util.Date;

@Data
public class SysNotice extends BaseEntity{
    // 主键
    private Long id;
    // 内容
    private String content;
    // 通知创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 被通知者id
    private Long notifier;
    // 1：未读，2：已读，3：删除
    private Integer state;

    @Override
    public void preInsert() {}

    @Override
    public void preUpdate() {}
}