package com.itheima.service;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderDetails;
import com.itheima.pojo.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Product getProductByID(Integer id);
    Member getMemberByID(Integer id);

    void updateInventory(Integer productID, Integer quantity);

    Integer addOrder(Order order);

    void addOrderDetail(OrderDetails orderDetails);

    List<OrderDetails> getOrderDetails(Integer id);
}
