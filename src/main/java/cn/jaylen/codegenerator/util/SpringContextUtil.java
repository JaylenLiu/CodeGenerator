package cn.jaylen.codegenerator.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ljl
 * @create 2018-09-04 10:11
 * @desc spring上下文工具
 **/
public class SpringContextUtil{
    /**
     * 获取spring上下文
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
        return applicationContext;
    }

    /**
     * 通过名称获取bean,
     * @param name: bean名称，首字母小写
     * @return
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return getApplicationContext().getBean(StringUtils.toLowerCaseFirstOne(name));
    }

    /**
     * 获取HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest getRequest(){
         return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HttpServletResponse对象
     * @return
     */
    public static HttpServletResponse getResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取HttpSession对象
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
}
