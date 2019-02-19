package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AgileEntity extends BaseEntity {
    private Long id;

    private Long schemaId;

    private Long conId;

    private String databaseName;

    private Long parentEntityId;

    private Integer entityType;

    private String className;

    private String tableName;

    private String parentField;

    private String relationField;

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