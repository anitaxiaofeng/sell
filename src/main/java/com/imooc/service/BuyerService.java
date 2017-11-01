package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * @Author Anita
 * @Date 2017/10/31 22:18
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
