package com.imooc.handler;

import com.imooc.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Anita
 * @Date 2017/11/13 19:16
 */
@ControllerAdvice
public class SellExceptionHandler {



    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){

        return new ModelAndView("redirect:/seller/order/list");
    }
}
