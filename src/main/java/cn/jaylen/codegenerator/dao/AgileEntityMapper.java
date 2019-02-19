package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.AgileEntity;
import cn.jaylen.codegenerator.entity.example.AgileEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgileEntityMapper {
    long countByExample(AgileEntityExample example);

    int deleteByExample(AgileEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgileEntity record);

    int insertSelective(AgileEntity record);

    List<AgileEntity> selectByExample(AgileEntityExample example);

    AgileEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AgileEntity record, @Param("example") AgileEntityExample example);

    int updateByExample(@Param("record") AgileEntity record, @Param("example") AgileEntityExample example);

    int updateByPrimaryKeySelective(AgileEntity record);

    int updateByPrimaryKey(AgileEntity record);
}