package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**商品（包含类目）
 * Created by George on 2017/10/29.
 */
@Data
public class ProductVO {

    @JsonProperty("name") //返回给前端时就是name，现在叫categoryName避免用混
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
