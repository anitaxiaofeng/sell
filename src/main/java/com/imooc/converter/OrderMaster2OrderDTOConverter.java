package com.imooc.converter;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.beans.BeanUtils;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Anita
 * @Date 2017/10/30 17:28
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
       return  orderMasterList.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());

    }
}