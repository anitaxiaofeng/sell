package com.imooc.service;

import com.imooc.dataobject.ProductCategory;


import java.util.List;

/**
 * Created by George on 2017/10/29.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    /**
     * 买家端一般通过Type查，上面两个是卖家端
     * */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
