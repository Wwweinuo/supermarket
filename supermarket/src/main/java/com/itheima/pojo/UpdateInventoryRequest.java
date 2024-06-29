package com.itheima.pojo;

import lombok.Data;

@Data
public class UpdateInventoryRequest {
    private Integer productID;
    private Integer quantity;
}
