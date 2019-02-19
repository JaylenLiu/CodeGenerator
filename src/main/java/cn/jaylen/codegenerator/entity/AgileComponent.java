package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties({"updateTime","createTime"})
@Data
public class AgileComponent extends BaseEntity {
    private Long id;

    private Long entityId;

    private Long keynameId;

    private String fieldName;

    private String label;

    private String defaultValue;

    private String componentType;

    private String placeholder;

    private Boolean isDisabled;

    private Boolean isVisibled;

    private Boolean isRequired;

    private Boolean isQuery;

    private Date createTime;

    private Date updateTime;

    @Override
    public void preInsert() {
        setCreateTime(new Date());
        setIsVisibled(true);
        setIsRequired(false);
        setIsDisabled(false);
        setIsQuery(false);
        setComponentType("input");
    }

    @Override
    public void preUpdate() {
        setUpdateTime(new Date());
    }
}