//package com.imooc.service.impl;
//
//import com.imooc.dto.OrderDTO;
//import com.imooc.enums.ResultEnum;
//import com.imooc.exception.SellException;
//import com.imooc.service.OrderService;
//import com.imooc.service.PayService;
//import com.imooc.utils.MathUtil;
//import com.lly835.bestpay.enums.BestPayTypeEnum;
//import com.lly835.bestpay.model.PayRequest;
//import com.lly835.bestpay.model.PayResponse;
//import com.lly835.bestpay.model.RefundRequest;
//import com.lly835.bestpay.model.RefundResponse;
//import com.lly835.bestpay.service.impl.BestPayServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
///**
// * Created by George on 2017/11/3.
// */
//@Service
//@Slf4j
//public class PayServiceImpl implements PayService {
//
//    private static final String ORDER_NAME = "微信点餐订餐";
//
//    @Autowired
//    private BestPayServiceImpl bestPayService;
//
//    @Autowired
//    private OrderService orderService;
//    @Override
//    public PayResponse create(OrderDTO orderDTO) {
//        PayRequest payRequest = new PayRequest();
//        payRequest.setOpenid(orderDTO.getBuyerOpenid());
//        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
//        payRequest.setOrderId(orderDTO.getOrderId());
//        payRequest.setOrderName(ORDER_NAME);
//        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
//        PayResponse payResponse = bestPayService.pay(payRequest);
//        //PayResponse 中有prepay_id和订单的参数，返回给前端
//        //前端视图使用PayResponse传回来的参数，然后就跳转到支付页面了
//         return payResponse;
//
//    }
//
//    @Override
//    public PayResponse notify(String notifyData){
//        //1.验证签名
//        //2.支付的状态
//        //3.支付金额
//        //4.支付人
//       PayResponse payResponse = bestPayService.asyncNotify(notifyData);
//        //修改订单的支付状态
//        String orderId = payResponse.getOrderId();
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if(orderDTO == null){
//              log.error("【微信支付】异步通知，订单不存在，orderId={}",orderId);
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        //判断金额是否一致
//        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
//            log.error("【微信支付】异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",orderId
//            ,payResponse.getOrderAmount(),orderDTO.getOrderAmount());
//            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
//        }
//
//        orderService.paid(orderDTO);
//
//        return payResponse;
//    }
//
//
//    //退款
//    @Override
//    public RefundResponse refund(OrderDTO orderDTO) {
//        RefundRequest refundRequest = new RefundRequest();
//        refundRequest.setOrderId(orderDTO.getOrderId());
//        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
//        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
//
//        RefundResponse refundResponse = bestPayService.refund(refundRequest);
//        return refundResponse;
//
//    }
//}
