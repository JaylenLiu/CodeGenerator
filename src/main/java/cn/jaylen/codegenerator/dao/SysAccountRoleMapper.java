package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysAccountRole;
import cn.jaylen.codegenerator.entity.example.SysAccountRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAccountRoleMapper {
    long countByExample(SysAccountRoleExample example);

    int deleteByExample(SysAccountRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAccountRole record);

    int insertSelective(SysAccountRole record);

    List<SysAccountRole> selectByExample(SysAccountRoleExample example);

    SysAccountRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAccountRole record, @Param("example") SysAccountRoleExample example);

    int updateByExample(@Param("record") SysAccountRole record, @Param("example") SysAccountRoleExample example);

    int updateByPrimaryKeySelective(SysAccountRole record);

    int updateByPrimaryKey(SysAccountRole record);
}