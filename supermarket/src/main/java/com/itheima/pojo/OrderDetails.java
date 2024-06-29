package com.itheima.pojo;

import lombok.Data;

import javax.persistence.*;

@Data

public class OrderDetails {
    private Integer orderDetailID;
    private Integer orderID;
    private Integer productID;
    private String productName;
    private Integer unitPrice;
    private Integer quantity;
    private Integer totalPrice;
}
