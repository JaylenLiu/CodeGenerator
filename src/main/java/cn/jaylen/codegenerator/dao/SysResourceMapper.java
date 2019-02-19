package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysResource;
import cn.jaylen.codegenerator.entity.example.SysResourceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysResourceMapper {
    long countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    // 根据账号id获取资源树
    List<SysResource> selectByAccountId(Long accountId);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}