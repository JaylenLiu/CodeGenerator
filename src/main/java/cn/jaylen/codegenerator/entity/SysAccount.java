package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import cn.jaylen.codegenerator.util.SpringContextUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysAccount extends BaseEntity {
    private Long id;

    private Long userId;

    private String username;

    private String password;

    // 1:禁用，2：正常， 3：锁定
    private Integer state;

    private Integer errorCount;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private String lastLoginIp;

    private String creator;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;

    private String updater;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    private Date updateTime;

    private String remark;

    @Override
    public void preInsert() {
        setCreator((String) SpringContextUtil.getRequest().getSession().getAttribute("realname"));
        setCreateTime(new Date());
        setErrorCount(0);
        setState(2);
    }

    @Override
    public void preUpdate() {
        setUpdater((String) SpringContextUtil.getRequest().getSession().getAttribute("realname"));
        setUpdateTime(new Date());
    }
}