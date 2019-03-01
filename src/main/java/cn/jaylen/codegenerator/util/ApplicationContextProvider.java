package cn.jaylen.codegenerator.util;

/**
 * @Author: Jaylen
 * @Description:
 * @Date: 2019/2/27 10:21
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    private ApplicationContextProvider() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static <T> T getBean(String name, Class<T> aClass) {
        return context.getBean(StringUtils.toLowerCaseFirstOne(name), aClass);
    }
    public static Object getBean(String name) {
        return context.getBean(StringUtils.toLowerCaseFirstOne(name));
    }
}