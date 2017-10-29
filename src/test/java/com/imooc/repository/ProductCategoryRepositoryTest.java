package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


/**
 * Created by George on 2017/10/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest(){
         ProductCategory productCategory = new ProductCategory("女生最爱", 10);
         ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,10);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotNull(result.size());

    }

}