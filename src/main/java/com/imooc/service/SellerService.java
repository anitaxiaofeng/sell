package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * @Author Anita
 * @Date 2017/11/13 16:44
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
