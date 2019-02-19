package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.AgileSchema;
import cn.jaylen.codegenerator.entity.example.AgileSchemaExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgileSchemaMapper {
    long countByExample(AgileSchemaExample example);

    int deleteByExample(AgileSchemaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgileSchema record);

    int insertSelective(AgileSchema record);

    List<AgileSchema> selectByExample(AgileSchemaExample example);

    AgileSchema selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AgileSchema record, @Param("example") AgileSchemaExample example);

    int updateByExample(@Param("record") AgileSchema record, @Param("example") AgileSchemaExample example);

    int updateByPrimaryKeySelective(AgileSchema record);

    int updateByPrimaryKey(AgileSchema record);
}