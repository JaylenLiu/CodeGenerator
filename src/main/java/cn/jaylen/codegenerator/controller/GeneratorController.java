package cn.jaylen.codegenerator.controller;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @create 2018-09-03 13:50
 * @desc 代码生成controller
 **/
@RestController
public class GeneratorController {
    @Autowired
    GeneratorService service;

    @PostMapping(value = "/generateCode")
    public Message generateCode(Long schemaId, String[] generateList){
        return service.generateCode(schemaId, generateList);
    }
}
