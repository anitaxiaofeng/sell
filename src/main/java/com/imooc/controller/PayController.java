//package com.imooc.controller;
//
//import com.imooc.dto.OrderDTO;
//import com.imooc.enums.ResultEnum;
//import com.imooc.exception.SellException;
//import com.imooc.service.OrderService;
//import com.imooc.service.PayService;
//import com.lly835.bestpay.model.PayResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by George on 2017/11/3.
// */
//@Controller
//@Slf4j
//@RequestMapping("/pay")
//public class PayController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private PayService payService;
//
//
//    @GetMapping("/create") //这是支付地址
//    public ModelAndView create(@RequestParam("orderId") String orderId,
//                               @RequestParam("returnUrl") String returnUrl){
//        Map<String, Object> map = new HashMap<>();
//        //1、查询订单
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if(orderDTO == null){
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        //2、发起支付
//        PayResponse payResponse = payService.create(orderDTO);
//        map.put("payResponse",payResponse);
//        map.put("returnUrl",returnUrl);
//        return new ModelAndView("pay/create",map);  //在templates文件夹下路径pay/create.ftl
//        //前端有一个js代码是可以根据payResponse传来的参数跳转到支付页面，并且在该代码中可以设置支付完之后的跳转页面returnUrl
//
//    }
//
//    //上一步完成支付之后，微信会返回一个异步通知，我们根据这个异步通知来修改订单的支付状态
//    @PostMapping("notify") //就是application.yml配置文件中wechat下的notifyUrl
//    public ModelAndView notify(@RequestBody String notifyData ){   //notifyData异步通知返回的值是一个xml字符串
//        //其中有一个transaction_id流水号唯一的凭证
//        payService.notify(notifyData);
//
//        //修改支付状态完成
//        //返回给微信处理结果
//        return  new ModelAndView("pay/success");//模版里面是要通知微信停止异步通知的xml代码
//
//    }
//
//
//
//}
