package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysRole;
import cn.jaylen.codegenerator.entity.example.SysRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    String getRoleNameByUsername(String username);
}