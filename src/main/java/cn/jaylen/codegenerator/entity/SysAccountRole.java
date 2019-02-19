package cn.jaylen.codegenerator.entity;

import lombok.Data;

@Data
public class SysAccountRole {
    private Long id;

    private Long accountId;

    private Long roleId;
}