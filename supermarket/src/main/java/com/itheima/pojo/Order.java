package com.itheima.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private Integer orderID;
    private Integer memberID;
    private Timestamp orderDate;
}
