package com.imooc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Anita
 * @Date 2017/11/13 17:30
 */
public class CookieUtil {

    public static void set(HttpServletResponse reponse, String name,
                           String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        reponse.addCookie(cookie);

    }
    public static Cookie get(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else{
            return null;
        }


    }
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> cookieMap = new HashMap<>();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
