package cn.jaylen.codegenerator.aspect;

import cn.jaylen.codegenerator.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * http请求切面，用于日志编写
 *
 * @author luxinglin
 */
@Aspect
@Component
public class HttpAspect {
    protected final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 设置切入点，api包下所有类的所有方法+baseController下所有方法
     */
    @Pointcut("execution(public * cn.jaylen.codegenerator.controller.*.*(..))")
    public void restPointcut() {
    }

    @Before("restPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        logBefore(joinPoint);
    }

    @After("restPointcut()")
    public void doAfter() {
        logAfter();
    }

    @AfterReturning(returning = "object", pointcut = "restPointcut()")
    public void doReturn(Object object) {
        logReturn(object, "return = {}");
    }

    /**
     * 进入方法前，取的参数信息
     *
     * @param joinPoint
     */
    private void logBefore(JoinPoint joinPoint) {
        logger.debug("---------------------------------------");
        logger.debug("start time: ".concat(DateFormatUtils.format(new Date(), DATE_TIME_FORMAT)));
        //记录http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //从request中获取http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
        //url
        logger.debug("url={}", request.getRequestURI());
        //method
        logger.debug("method={}", request.getMethod());
        //ip
        logger.debug("ip={}", request.getRemoteAddr());
        //类方法
        logger.debug("class_method={}", joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
        try {
            //参数
            logger.info("args={}", joinPoint.getArgs() != null ? JsonUtil.toJson(joinPoint.getArgs(), JsonInclude.Include.NON_NULL) : "");
        } catch (Exception ex) {
            logger.info("args={}", joinPoint.getArgs() != null ? joinPoint.toString() : "");
        }
    }

    /**
     * 方法执行完成，写入Action日志表
     */
    private void logAfter() {
        logger.info("end time: ".concat(DateFormatUtils.format(new Date(), DATE_TIME_FORMAT)));
    }
    /**
     * @param object
     * @param s
     */
    private void logReturn(Object object, String s) {
        logger.debug(s, JsonUtil.toJson(object, JsonInclude.Include.NON_NULL));
    }
}