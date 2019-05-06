package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.DatabaseConnection;
import java.util.List;

import cn.jaylen.codegenerator.entity.example.DatabaseConnectionExample;
import org.apache.ibatis.annotations.Param;

public interface DatabaseConnectionMapper {
    long countByExample(DatabaseConnectionExample example);

    int deleteByExample(DatabaseConnectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DatabaseConnection record);

    int insertSelective(DatabaseConnection record);

    List<DatabaseConnection> selectByExample(DatabaseConnectionExample example);

    DatabaseConnection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DatabaseConnection record, @Param("example") DatabaseConnectionExample example);

    int updateByExample(@Param("record") DatabaseConnection record, @Param("example") DatabaseConnectionExample example);

    int updateByPrimaryKeySelective(DatabaseConnection record);

    int updateByPrimaryKey(DatabaseConnection record);
}