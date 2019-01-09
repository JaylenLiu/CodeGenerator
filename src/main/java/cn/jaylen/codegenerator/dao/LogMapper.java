package cn.jaylen.codegenerator.dao;

import static cn.jaylen.codegenerator.dao.LogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.jaylen.codegenerator.entity.Log;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface LogMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Log> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("LogResult")
    Log selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="LogResult", value = {
        @Result(column="log_id", property="logId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="invoker", property="invoker", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Log> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(log);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, log);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long logId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, log)
                .where(logId, isEqualTo(logId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Log record) {
        return insert(SqlBuilder.insert(record)
                .into(log)
                .map(logId).toProperty("logId")
                .map(userName).toProperty("userName")
                .map(invoker).toProperty("invoker")
                .map(logType).toProperty("logType")
                .map(createTime).toProperty("createTime")
                .map(content).toProperty("content")
                .map(remark).toProperty("remark")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Log record) {
        return insert(SqlBuilder.insert(record)
                .into(log)
                .map(logId).toPropertyWhenPresent("logId", record::getLogId)
                .map(userName).toPropertyWhenPresent("userName", record::getUserName)
                .map(invoker).toPropertyWhenPresent("invoker", record::getInvoker)
                .map(logType).toPropertyWhenPresent("logType", record::getLogType)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(content).toPropertyWhenPresent("content", record::getContent)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Log>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, logId, userName, invoker, logType, createTime, content, remark)
                .from(log);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Log>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, logId, userName, invoker, logType, createTime, content, remark)
                .from(log);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Log selectByPrimaryKey(Long logId_) {
        return SelectDSL.selectWithMapper(this::selectOne, logId, userName, invoker, logType, createTime, content, remark)
                .from(log)
                .where(logId, isEqualTo(logId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Log record) {
        return UpdateDSL.updateWithMapper(this::update, log)
                .set(logId).equalTo(record::getLogId)
                .set(userName).equalTo(record::getUserName)
                .set(invoker).equalTo(record::getInvoker)
                .set(logType).equalTo(record::getLogType)
                .set(createTime).equalTo(record::getCreateTime)
                .set(content).equalTo(record::getContent)
                .set(remark).equalTo(record::getRemark);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Log record) {
        return UpdateDSL.updateWithMapper(this::update, log)
                .set(logId).equalToWhenPresent(record::getLogId)
                .set(userName).equalToWhenPresent(record::getUserName)
                .set(invoker).equalToWhenPresent(record::getInvoker)
                .set(logType).equalToWhenPresent(record::getLogType)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(content).equalToWhenPresent(record::getContent)
                .set(remark).equalToWhenPresent(record::getRemark);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Log record) {
        return UpdateDSL.updateWithMapper(this::update, log)
                .set(userName).equalTo(record::getUserName)
                .set(invoker).equalTo(record::getInvoker)
                .set(logType).equalTo(record::getLogType)
                .set(createTime).equalTo(record::getCreateTime)
                .set(content).equalTo(record::getContent)
                .set(remark).equalTo(record::getRemark)
                .where(logId, isEqualTo(record::getLogId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Log record) {
        return UpdateDSL.updateWithMapper(this::update, log)
                .set(userName).equalToWhenPresent(record::getUserName)
                .set(invoker).equalToWhenPresent(record::getInvoker)
                .set(logType).equalToWhenPresent(record::getLogType)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(content).equalToWhenPresent(record::getContent)
                .set(remark).equalToWhenPresent(record::getRemark)
                .where(logId, isEqualTo(record::getLogId))
                .build()
                .execute();
    }
}