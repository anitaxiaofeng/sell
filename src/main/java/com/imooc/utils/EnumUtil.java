package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @Author: Anita
 * @Date: Created in 22:08 2017/11/6
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
