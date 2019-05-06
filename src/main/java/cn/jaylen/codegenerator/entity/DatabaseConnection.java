package cn.jaylen.codegenerator.entity;

import cn.jaylen.codegenerator.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.util.Date;

@Data
public class DatabaseConnection extends BaseEntity{
    private String id;
    private String connName;
    // 1:mysql,2:sql server 3:oracle
    private Integer connType;
    private String connIp;
    private Integer connPort;
    private String connUsername;
    private String connPwd;
    // oracle SID(服务名)
    private String sid;
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String remark;

    @Override
    public void preInsert() {}

    @Override
    public void preUpdate() {}
}