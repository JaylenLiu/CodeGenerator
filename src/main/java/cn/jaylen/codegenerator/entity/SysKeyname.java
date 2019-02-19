package cn.jaylen.codegenerator.entity;


import cn.jaylen.codegenerator.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class SysKeyname extends BaseEntity {
    private Long id;

    private Long parentId;

    private String keyname;

    private Integer keyType;

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