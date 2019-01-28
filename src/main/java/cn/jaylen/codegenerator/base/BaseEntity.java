package cn.jaylen.codegenerator.base;

/**
 * @author ljl
 * @create 2018-06-26 9:37
 * @desc 基础实体类
 **/
public abstract class BaseEntity {
    public abstract void preInsert();
    public abstract void preUpdate();
}
