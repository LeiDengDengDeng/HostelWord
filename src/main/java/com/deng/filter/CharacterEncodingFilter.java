package com.deng.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by deng on 2017/3/8.
 */
public class CharacterEncodingFilter implements Filter {
    protected FilterConfig filterConfig = null;
    protected String encoding = "utf-8";


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpServletResponse httpRes = (HttpServletResponse) response;

            httpReq.setCharacterEncoding("utf-8");
            httpRes.setContentType("text/html;charset=utf-8");
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }


    public void destroy() {
        filterConfig = null;
        encoding = null;
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("charset");
    }
}
