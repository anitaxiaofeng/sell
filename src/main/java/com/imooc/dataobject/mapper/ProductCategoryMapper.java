package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * @Author: Anita
 * @Date: Created in 14:05 2017/11/17
 */
public interface ProductCategoryMapper {

    @Insert("insert into product category(category_name,category_type)value(" +
            "#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=Integer})")
    int insertByMap(Map<String,Object> map);



    @Insert("insert into product category(category_name,category_type)value(" +
            "#{category_name,jdbcType=VARCHAR},#{categoryType,jdbcType=Integer})")
    int insertByObject(ProductCategory productCategory);


    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column="category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType")Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type=#{categoryType}")
    int deleteByCategoryType(Integer categoryType);


}
