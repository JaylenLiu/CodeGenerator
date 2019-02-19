package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class DatabaseConnection extends BaseEntity {
    private Long id;

    private String connName;

    private Integer connType;

    private String connIp;

    private Integer connPort;

    private String connUsername;

    private String connPwd;

    private Date createTime;

    private Date updateTime;

    private String remark;

    @Override
    public void preInsert() {
        setCreateTime(new Date());
    }

    @Override
    public void preUpdate() {
        setUpdateTime(new Date());
    }
}