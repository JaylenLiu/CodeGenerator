package cn.jaylen.codegenerator.dao;

import cn.jaylen.codegenerator.entity.SysKeyvalue;
import cn.jaylen.codegenerator.entity.example.SysKeyvalueExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysKeyvalueMapper {
    long countByExample(SysKeyvalueExample example);

    int deleteByExample(SysKeyvalueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysKeyvalue record);

    int insertSelective(SysKeyvalue record);

    List<SysKeyvalue> selectByExample(SysKeyvalueExample example);

    SysKeyvalue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysKeyvalue record, @Param("example") SysKeyvalueExample example);

    int updateByExample(@Param("record") SysKeyvalue record, @Param("example") SysKeyvalueExample example);

    int updateByPrimaryKeySelective(SysKeyvalue record);

    int updateByPrimaryKey(SysKeyvalue record);

    List<SysKeyvalue> getKeyValuesByKeyname(String keyname);

    /**
     * 判断产品线是否存在
     * @param productLine
     * @return
     */
    long isExistProductLine(String productLine);
}