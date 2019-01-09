package cn.jaylen.codegenerator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import javax.annotation.Generated;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Log {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long logId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String userName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String invoker;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String logType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String remark;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getLogId() {
        return logId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUserName() {
        return userName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getInvoker() {
        return invoker;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setInvoker(String invoker) {
        this.invoker = invoker == null ? null : invoker.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLogType() {
        return logType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getContent() {
        return content;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRemark() {
        return remark;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}