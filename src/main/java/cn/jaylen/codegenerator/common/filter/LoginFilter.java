package cn.jaylen.codegenerator.common.filter;

/**
 * @Author: Jaylen
 * @Description:
 * @Date: 2019/5/5 10:57
 */

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截，如检测到session 丢失，则返回请求
 */
//@Component
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURL().toString();
        if (!url.contains("login") && !url.contains("index.html") && session.getAttribute("username") == null) {
            sendMessage(httpResponse, Message.errorMessage(600, "未登录系统！"));
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    private void sendMessage(HttpServletResponse response, Message message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(message));
    }
}
