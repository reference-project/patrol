package com.slk.patrol.zull.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class ExceptionFilter extends ZuulFilter {
    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";

    private Logger log = LoggerFactory.getLogger(ExceptionFilter.class);

    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        SimpleRouteLocator a;
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.containsKey("throwable")||ctx.containsKey("responseStatusCode");
    }

    @Override
    public Object run() {
        //获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        try {
            HttpServletRequest request = ctx.getRequest();
            if (ctx.containsKey("throwable")) {
                Throwable e = (Exception) ctx.get("throwable");
                Throwable re = getOriginException(e);
                String message=null;
                if(re instanceof java.net.ConnectException){
                    message = "Real Service Connection refused";
                    log.error("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
                }else if(re instanceof java.net.SocketTimeoutException){
                    message = "Real Service Timeout";
                    log.error("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
                }else if(re instanceof com.netflix.client.ClientException){
                    message = re.getMessage();
                    log.error("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
                }else{
                    log.warn("Error during filtering",e);
                }
                ctx.setSendZuulResponse(false);
                ctx.set("flag","false");
                ctx.setResponseStatusCode(404);
            }else if(ctx.containsKey("responseStatusCode")){
                Integer code=(Integer)ctx.get("responseStatusCode");
                if(null!=code&&code!=200&&code!=222){
                    log.error("uri:{},error:{}" ,ctx.getRouteHost()+request.getRequestURI(),"");
                    ctx.setSendZuulResponse(false);
                    ctx.set("flag","false");
                    ctx.setResponseStatusCode(404);
                }
            }

        } catch (Exception e) {
            String error = "Error during filtering[ExceptionFilter]";
            log.error(error,e);
        }
        return null;

    }

    private Throwable getOriginException(Throwable e){
        e = e.getCause();
        while(e.getCause() != null){
            e = e.getCause();
        }
        return e;
    }
}
