package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysAccount;
import cn.jaylen.codegenerator.entity.example.SysAccountExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysAccountMapper {
    long countByExample(SysAccountExample example);

    int deleteByExample(SysAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAccount record);

    int insertSelective(SysAccount record);

    List<SysAccount> selectByExample(SysAccountExample example);

    SysAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAccount record, @Param("example") SysAccountExample example);

    int updateByExample(@Param("record") SysAccount record, @Param("example") SysAccountExample example);

    int updateByPrimaryKeySelective(SysAccount record);

    int updateByPrimaryKey(SysAccount record);

    List<Map<String, Object>> selectAccountLists(@Param("state") int state, @Param("searchKey") String searchKey);
}