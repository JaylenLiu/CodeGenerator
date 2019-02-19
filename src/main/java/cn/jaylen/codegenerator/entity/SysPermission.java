package cn.jaylen.codegenerator.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysPermission {
    private Long id;

    private String permissionName;

    private Date createTime;

    private Date updateTime;

    private String remark;
}