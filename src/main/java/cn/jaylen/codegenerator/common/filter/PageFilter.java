package cn.jaylen.codegenerator.common.filter;

import cn.jaylen.codegenerator.util.PageUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ljl
 * @create 2018-06-28 15:24
 * @desc 拦截请求，获取分页信息以及request、response对象，并将其存入线程变量中
 **/
@Component
@WebFilter(urlPatterns = "/*", filterName = "pageFilter")
public class PageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 将分页信息存入线程变量
        String pageNum = httpRequest.getParameter("pageNum");
        String pageSize = httpRequest.getParameter("pageSize");
        if (!StringUtils.isNullOrEmpty(pageNum)){
            PageUtil.setPageNum(Integer.parseInt(pageNum));
        } else

        if (!StringUtils.isNullOrEmpty(pageSize)){
            PageUtil.setPageSize(Integer.parseInt(pageSize));
        }

        try{
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 清除线程变量
            PageUtil.removePageNum();
            PageUtil.removePageSize();
        }

    }

    @Override
    public void destroy() {
    }
}
