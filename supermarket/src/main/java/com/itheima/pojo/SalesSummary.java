package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesSummary {
    private Integer productID;
    private String productName;
    private Integer quantitySold;
    private Double totalSales;

}
