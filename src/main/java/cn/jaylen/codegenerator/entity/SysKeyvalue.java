package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysKeyvalue extends BaseEntity {
    private Long id;

    private Long keynameId;

    private String keyvalue;

    private String description;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd")
    private Date updateTime;

    @Override
    public void preInsert() {
        setCreateTime(new Date());
    }

    @Override
    public void preUpdate() {
        setUpdateTime(new Date());
    }
}