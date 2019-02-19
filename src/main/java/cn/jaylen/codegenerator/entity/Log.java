package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Log extends BaseEntity {
    private Long logId;

    private String userName;

    private String invoker;

    private String logType;

    private String content;

    private Date createTime;

    private String remark;

    public Log() {
    }

    public Log(String userName, String logType, String content, String invoker) {
        this.userName = userName;
        this.invoker = invoker;
        this.logType = logType;
        this.content = content;
    }
    @Override
    public void preInsert() {
        setCreateTime(new Date());
    }

    @Override
    public void preUpdate() {

    }
}