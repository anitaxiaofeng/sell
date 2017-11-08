package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.exception.SellException;
import com.imooc.form.CategoryForm;
import com.imooc.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: Anita
 * @Date: Created in 12:55 2017/11/8
 */

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam( value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if(categoryId !=null){
          ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }
        return new ModelAndView("category/index", map);
    }


    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        //等于null说明是新增的
        try{
        if(form.getCategoryId()!=null){
          productCategory = categoryService.findOne(form.getCategoryId());
        }

        BeanUtils.copyProperties(form,productCategory);
        categoryService.save(productCategory);
        } catch(SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","新增类目成功");
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("/common/success",map);

    }
}
