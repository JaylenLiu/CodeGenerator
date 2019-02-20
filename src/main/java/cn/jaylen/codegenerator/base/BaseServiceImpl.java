package cn.jaylen.codegenerator.base;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.util.SpringContextUtil;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author ljl
 * @create 2018-09-03 17:18
 * @desc 公共service,
 **/
public class BaseServiceImpl<T> implements BaseService<T>{
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private Object mapperObj = null;

    private Object getMapperObj (){
        if (mapperObj == null) {
            mapperObj = SpringContextUtil.getBean(entityClass.getSimpleName() + "Mapper");
        }
        return mapperObj;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        try {
            Method method = getMapperObj().getClass().getMethod("deleteByPrimaryKey", Long.class);
            return (int)method.invoke(mapperObj, id);
        } catch (Exception e) {
            logger.error("删除"+entityClass.getSimpleName()+"失败", e);
            return -1;
        }
    }
    @Override
    public int insert(T record) {
        try {
            Method method = getMapperObj().getClass().getMethod("insert", entityClass);
            return (int)method.invoke(mapperObj, record);
        } catch (Exception e) {
            logger.error("插入"+entityClass.getSimpleName()+"失败", e);
            return -1;
        }
    }

    @Override
    public int insertSelective(T record) {
        try {
            Method method = getMapperObj().getClass().getMethod("insertSelective", entityClass);
            return (int)method.invoke(mapperObj, record);
        } catch (Exception e) {
            logger.error("插入"+entityClass.getSimpleName()+"失败", e);
            return -1;
        }
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        try {
            Method method = getMapperObj().getClass().getMethod("selectByPrimaryKey", Long.class);
            return (T) method.invoke(mapperObj, id);
        } catch (Exception e) {
            logger.error("获取"+entityClass.getSimpleName()+"失败", e);
            return null;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        try {
            Method method = getMapperObj().getClass().getMethod("updateByPrimaryKeySelective", entityClass);
            return (int) method.invoke(mapperObj, record);
        } catch (Exception e) {
            logger.error("更新"+entityClass.getSimpleName()+"失败", e);
            return -1;
        }
    }

    @Override
    public int updateByPrimaryKey(T record) {
        try {
            Method method = getMapperObj().getClass().getMethod("updateByPrimaryKey", entityClass);
            return (int) method.invoke(mapperObj, record);
        } catch (Exception e) {
            logger.error("更新"+entityClass.getSimpleName()+"失败", e);
            return -1;
        }
    }

}
