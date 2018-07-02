package com.slk.patrol.framework.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class CookieUtils {
    private CookieUtils(){}
    private static final String CHARSET_NAME="UTF-8";
    private static final int MAXAGE=-1;
    private static final String PATH="/";

    public static Cookie[] getCookies(){
        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        Cookie[] cookies=request.getCookies();
        return cookies;
    }

    public static void showCookie(){
        Cookie[] c=getCookies();
        for (int i = 0; i < (c == null ? 0 : c.length); i++) {
            System.out.println("一条cookie____  name: " + c[i].getName() + "  || value: " + c[i].getValue());
        }
    }

    public static void saveCookie(Cookie cookie){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String value=getCookies(cookie.getName());
        HttpServletResponse response = attributes.getResponse();
       /* cookie.setMaxAge(MAXAGE);
        cookie.setPath(PATH);*/
        response.addCookie(cookie);

    }

    public static String getCookies(String name){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        /*HttpSession session = attributes.getRequest().getSession();
        String value=session.getAttribute(name).toString();*/
            try{
                Cookie[] cookies=getCookies();
                for(int i=0;i<(cookies==null?0:cookies.length);i++){
                    if(name.equalsIgnoreCase(cookies[i].getName())){
                        return URLEncoder.encode(cookies[i].getValue(),CHARSET_NAME);
                    }
                }
            }catch (Exception e){
                System.out.println("-------获取失败-------"+e.getMessage());
            }
            return null;

    }

    public static void setCookie(String name,Object value){
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String v= URLEncoder.encode(JSONUtilsInherit.toJSONString(value),CHARSET_NAME);
            if(getCookies(name)==null){
                Cookie cookie=new Cookie(name,v);
                HttpServletResponse response = attributes.getResponse();
                cookie.setMaxAge(MAXAGE);
                cookie.setPath(PATH);
                response.addCookie(cookie);
            }
        }catch(Exception e){
                System.out.println("-------添加cookie 失败！--------"+e.getMessage());
        }
    }

    public static <T> T getCookies(String name,Class<T> clazz){
        Cookie[] cookies=getCookies();
        String v=null;
        try{
        for (int i=0;i<(cookies==null?0:cookies.length);i++){
            if((name).equalsIgnoreCase(cookies[i].getName())){
                v=URLEncoder.encode(cookies[i].getValue(),CHARSET_NAME);
            }
        }
        if(v!=null){
            return JSONUtilsInherit.objectFromJsonStr(v,clazz);
        }
        }catch(Exception e){
            System.out.println("------获取 clazz Cookie 失败----- " + e.getMessage());
        }
        return null;
    }


    public static void removeCookie(String name){
        try{
            Cookie[] cookies=getCookies();
            for (int i=0;i<(cookies==null?0:cookies.length);i++){
                if(name.equalsIgnoreCase(cookies[i].getName())){
                    Cookie cookie=new Cookie(name,"");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    saveCookie(cookie);
                }
            }
        }catch(Exception e){
            System.out.println("----------删除失败了------------"+e.getMessage());
        }
    }
}
