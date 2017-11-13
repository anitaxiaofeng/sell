package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Anita
 * @Date 2017/11/13 16:39
 */
@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
       SellerInfo findByOpenid(String openid);
}
