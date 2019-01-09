package cn.jaylen.codegenerator.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class LogDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Log log = new Log();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> logId = log.logId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> userName = log.userName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> invoker = log.invoker;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> logType = log.logType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = log.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> content = log.content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> remark = log.remark;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Log extends SqlTable {
        public final SqlColumn<Long> logId = column("log_id", JDBCType.BIGINT);

        public final SqlColumn<String> userName = column("user_name", JDBCType.VARCHAR);

        public final SqlColumn<String> invoker = column("invoker", JDBCType.VARCHAR);

        public final SqlColumn<String> logType = column("log_type", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.LONGVARCHAR);

        public Log() {
            super("log");
        }
    }
}