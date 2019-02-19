package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysKeyname;
import cn.jaylen.codegenerator.entity.example.SysKeynameExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysKeynameMapper {
    long countByExample(SysKeynameExample example);

    int deleteByExample(SysKeynameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysKeyname record);

    int insertSelective(SysKeyname record);

    List<SysKeyname> selectByExample(SysKeynameExample example);

    SysKeyname selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysKeyname record, @Param("example") SysKeynameExample example);

    int updateByExample(@Param("record") SysKeyname record, @Param("example") SysKeynameExample example);

    int updateByPrimaryKeySelective(SysKeyname record);

    int updateByPrimaryKey(SysKeyname record);
}