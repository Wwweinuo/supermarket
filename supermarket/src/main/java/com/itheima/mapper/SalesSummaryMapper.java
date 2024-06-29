package com.itheima.mapper;

import com.itheima.pojo.SalesSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface SalesSummaryMapper {
    @Select("SELECT productID, productName, SUM(quantity) AS quantitySold, SUM(totalPrice) AS totalSales FROM orderDetails GROUP BY productID, productName")
    List<SalesSummary> getSalesD();
}
