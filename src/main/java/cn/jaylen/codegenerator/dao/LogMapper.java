package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.Log;
import cn.jaylen.codegenerator.entity.example.LogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}