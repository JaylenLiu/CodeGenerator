package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysRole extends BaseEntity {
    private Long id;

    private String roleName;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
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