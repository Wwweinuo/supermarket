package com.itheima.mapper;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderDetails;
import com.itheima.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    //根据商品ID查找商品
    @Select("select * from product where productID = #{id}")
    Product getProductByID(Integer id);

    //更新库存
    @Update("UPDATE product SET stock = stock - #{quantity} WHERE productID = #{productID}")
    void updateInventory(@Param("productID") Integer productID, @Param("quantity") Integer quantity);

    @Select("select * from member where memberID = #{id}")
    Member getMemberByID(Integer id);

    @Insert("insert into orders (memberID, orderDate) value (#{memberID},#{orderDate})")
    @Options(useGeneratedKeys = true, keyProperty = "orderID")
    void addOrder(Order order);

    @Insert("insert into orderdetails (orderID, productID, productName, unitPrice, quantity, totalPrice) " +
            "value (#{orderID},#{productID},#{productName},#{unitPrice},#{quantity},#{totalPrice})")
    void addOrderDetail(OrderDetails orderDetails);

    @Select("select * from orderdetails where orderID = #{id}")
    List<OrderDetails> getOrderDetails(Integer id);
}
