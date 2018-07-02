package com.slk.workstask.framework.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorCommon extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object obj = httpServletRequest.getSession().getAttribute("user");
        String requestUrl = httpServletRequest.getRequestURL().toString();
       /* System.out.println(httpServletRequest.getSession().getMaxInactiveInterval());*/
        boolean flag=false;
        if(requestUrl.contains("login")||requestUrl.contains("release")||requestUrl.contains("phone")){
            flag=true;
        }else{
            if (obj != null) {
                flag= true;
            }
        }
        if(!flag){
            httpServletResponse.setStatus(222);
        }
        return flag;
    }
}
