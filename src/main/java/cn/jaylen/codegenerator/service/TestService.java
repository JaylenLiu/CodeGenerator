package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.dao.LogMapper;
import cn.jaylen.codegenerator.entity.Log;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.jaylen.codegenerator.dao.LogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @Author: Jaylen
 * @Description:
 * @Date: 2019/1/9 17:06
 */
@RestController
public class TestService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @GetMapping("/test")
    public Message test(){
        SqlSession session = sqlSessionFactory.openSession();
        LogMapper mapper = session.getMapper(LogMapper.class);
        SelectStatementProvider selectStatement = select(logId,userName,invoker)
                .from(log)
                .where(logId, isLessThan(20l))
                .build()
                .render(RenderingStrategy.MYBATIS3);

        List<Log> logs = mapper.selectMany(selectStatement);
        return Message.successMessage(logs);
    }
}
