package cn.jaylen.codegenerator.base;

/**
 * @author ljl
 * @create 2018-09-04 9:37
 * @desc 通用service接口层
 **/
public interface BaseService<T> {

    int deleteByPrimaryKey(Long logId);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
