package cn.jaylen.codegenerator.common.exception;

import cn.jaylen.codegenerator.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloablExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GloablExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message handle(Exception e) {
        logger.error("【系统异常】", e);
        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            return Message.errorMessage(customException.getHttpCode(), customException.getMessage());
        }else {
            return Message.errorMessage(500,HttpCodeEnum.HTTP_500.getMsg());
        }
    }
}
