package cn.jaylen.codegenerator.common.filter;

import cn.jaylen.codegenerator.common.Message;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ljl
 * @create 2018-07-05 15:55
 * @desc 登录拦截器
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("username"));
        if (session.getAttribute("username") == null) {
            sendMessage(response, Message.errorMessage(600, "未登录系统！"));
            return false;
        }
        if (!isSessionLive(session)) {
            sendMessage(response, Message.errorMessage(600, "身份验证过期！"));
            return false;
        }
//        else {
//            ServletContext application= session.getServletContext();
//            String username = (String) session.getAttribute("username");
//            Map<String, String> loginMap = (Map<String, String>)application.getAttribute("loginMap");
//            if (!session.getId().equals(loginMap.get(username))) {
//                sendMessage(response, Message.errorMessage(600, "用户已在异地登录！"));
//                return false;
//            }
//        }
        return true;
    }

    /**
     * 判断session是否过期
     * @return
     */
    private boolean isSessionLive(HttpSession session){
        ServletContext application= session.getServletContext();
        String username = (String) session.getAttribute("username");
        Map<String, HashSet> loginMap = (Map<String, HashSet>)application.getAttribute("loginMap");
        if (loginMap != null) {
            if (loginMap.containsKey(username)) {
                HashSet loginSet = loginMap.get(username);
                Iterator<String> iterator =  loginSet.iterator();
                while(iterator.hasNext()){
                    String sessionId = iterator.next().split(":")[0];
                    if (session.getId().equals(sessionId)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void sendMessage(HttpServletResponse response, Message message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(JSONObject.toJSONString(message));
    }
}
