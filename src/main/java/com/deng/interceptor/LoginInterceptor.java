package com.deng.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2/18 0018.
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        List<String> unlimitedPages = new ArrayList<String>();
        unlimitedPages.add("/front/homepage.jsp");
        unlimitedPages.add("/front/login.jsp");
        unlimitedPages.add("/front/register.jsp");
        unlimitedPages.add("/back/pages/login.jsp");
        unlimitedPages.add("/back/pages/register.jsp");

        String url = request.getRequestURI();
        // unlimitedPages代表可以直接访问的页面
        if (isInList(url, unlimitedPages)) {
            System.out.println("unlimited");
            return true;
        }

        // 获取Session
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("type");

        if (type != null) {
            if (type.equals("member") && url.contains("front")) {
                return true;
            } else if (type.equals("manager") && url.contains("manager")) {
                return true;
            } else if (type.equals("hostel") && url.contains("hostel")) {
                return true;
            }
        }

        System.out.println("无法访问");
        response.sendRedirect("http://localhost:8089/front/homepage.jsp");
        return false;
    }

    public boolean isInList(String url, List<String> list) {
        for (String suffix : list) {
            if (url.contains(suffix)) return true;
        }
        return false;
    }
}
