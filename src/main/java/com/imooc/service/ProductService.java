package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * 商品
 * Created by George on 2017/10/29.
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /*
    * 查询所有在架列表
    * */
    List<ProductInfo> findUpAll();

    //可以分页查询
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    void onSale(String productId);

    //下架
    void offSale(String productId);

}
