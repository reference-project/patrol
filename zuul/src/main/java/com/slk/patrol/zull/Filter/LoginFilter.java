package com.slk.patrol.zull.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();
        boolean flag = true;
        // 请求URL内不包含login 或者inspection前端巡检 即执行run()
        if(requestUrl.contains("checkLogin") || requestUrl.contains("detection"))
            flag = false;
        return  flag;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession httpSession = request.getSession();
        // 若session中不包含userId，则这次请求视为未登录请求，不给予路由，而提示“请登录”
        String userId=null;
        if (httpSession.getAttribute("userId") == null) {
            Cookie[] cookies = request.getCookies();
            if(null!=cookies){
                for(Cookie cookie : cookies){
                    if("userId".equals(cookie.getName())){
                        userId = cookie.getValue();
                    }
                }
            }
            //如果cookie==null或者userId为null的话，登录失败
            if(userId==null||"".equals(userId)){
                logger.warn("还未登录");
                ctx.setSendZuulResponse(false);
                ctx.set("flag","false");
                ctx.setResponseStatusCode(222);
            }
        }
        return null;
    }
}
