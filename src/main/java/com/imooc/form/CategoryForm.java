package com.imooc.form;

import lombok.Data;


/**
 * @Author: Anita
 * @Date: Created in 13:15 2017/11/8
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
