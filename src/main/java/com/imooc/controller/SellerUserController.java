package com.imooc.controller;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author Anita
 * @Date 2017/11/13 17:02
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {
    @Autowired
    SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid")String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){
        //1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo==null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,
                expire, TimeUnit.SECONDS);

        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);

        return new ModelAndView("redirect:/seller/order/list");
        //这是登陆状态存了一个token，(登陆之前是没有token的，再登陆时才生成token)下次判断是否登陆，则从cookie中获取token值，再从缓存中获取到openid，
        //表示该用户已经登陆
        //并且每次跳转到页面时总要验证是否有token，没有token证明没有登陆，则跳到登陆页面
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response
    ,Map<String, Object> map){
        //1.从cookie中查询
        Cookie cookie =CookieUtil.get(request,CookieConstant.TOKEN);
        //2.清除redis

        if(cookie!=null){
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        }
        //3.清楚cookie
        CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

}
