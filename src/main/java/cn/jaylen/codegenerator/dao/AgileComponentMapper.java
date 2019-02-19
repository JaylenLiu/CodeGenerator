package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.AgileComponent;
import cn.jaylen.codegenerator.entity.example.AgileComponentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgileComponentMapper {
    long countByExample(AgileComponentExample example);

    int deleteByExample(AgileComponentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgileComponent record);

    int insertSelective(AgileComponent record);

    List<AgileComponent> selectByExample(AgileComponentExample example);

    AgileComponent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AgileComponent record, @Param("example") AgileComponentExample example);

    int updateByExample(@Param("record") AgileComponent record, @Param("example") AgileComponentExample example);

    int updateByPrimaryKeySelective(AgileComponent record);

    int updateByPrimaryKey(AgileComponent record);
}