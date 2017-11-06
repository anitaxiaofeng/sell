//package com.imooc.controller;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
///**
// * Created by George on 2017/11/2.
// */
//@Slf4j
//@RestController
//@RequestMapping("/weixin")
//public class WeixinController {
//    //用户同意授权之后，微信会调用这个接口并附上code的值，然后我们相当于
//    //第三方用这个code值去微信获取openid(access_token)
//    //appid和secret都是公众帐号上可以获得的
//
//
//    @GetMapping("/auth")
//    public void auth(@RequestParam("code")String code){
//          log.info("code={}",code);
//       String url = "http://api.weixin.qq.com/sns/oauth2/access_token?appid=  &secret=  &code=" +code+
//               "&grant_type=authorization_code";
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForObject(url , String.class);
//    }
//}
