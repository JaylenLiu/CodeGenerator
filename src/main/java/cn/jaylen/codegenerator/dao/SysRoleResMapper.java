package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysRoleRes;
import cn.jaylen.codegenerator.entity.example.SysRoleResExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleResMapper {
    long countByExample(SysRoleResExample example);

    int deleteByExample(SysRoleResExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleRes record);

    int insertSelective(SysRoleRes record);

    List<SysRoleRes> selectByExample(SysRoleResExample example);

    SysRoleRes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleRes record, @Param("example") SysRoleResExample example);

    int updateByExample(@Param("record") SysRoleRes record, @Param("example") SysRoleResExample example);

    int updateByPrimaryKeySelective(SysRoleRes record);

    int updateByPrimaryKey(SysRoleRes record);
}