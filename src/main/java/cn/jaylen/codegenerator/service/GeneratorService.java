package cn.jaylen.codegenerator.service;


import cn.jaylen.codegenerator.common.Message;

/**
 * @author ljl
 * @create 2018-09-03 13:51
 * @desc service
 **/
public interface GeneratorService {
    Message generateCode(Long schemaId, String[] generateList);
}
