package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AgileSchema extends BaseEntity {
    private Long id;

    private String schemaName;

    private String moduleName;

    private String moduleDesc;

    private String packagePath;

    private Date createTime;

    private Date updateTime;

    private String remark;
    @Override
    public void preInsert() {
        this.setCreateTime(new Date());
    }

    @Override
    public void preUpdate() {
        this.setUpdateTime(new Date());
    }
}