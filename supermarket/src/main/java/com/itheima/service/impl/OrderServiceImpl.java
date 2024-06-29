package com.itheima.service.impl;

import com.itheima.mapper.OrderMapper;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderDetails;
import com.itheima.pojo.Product;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Product getProductByID(Integer id){
        return orderMapper.getProductByID(id);
    }
    @Override
    public Member getMemberByID(Integer id){
        return orderMapper.getMemberByID(id);
    }
    @Override
    public Integer addOrder(Order order){
        orderMapper.addOrder(order);
        System.out.println("测试id---------------"+order.getOrderID());
        return order.getOrderID();
    }
    @Override
    public void addOrderDetail(OrderDetails orderDetails){
        orderMapper.addOrderDetail(orderDetails);
    }

    @Override
    public void updateInventory(Integer productID, Integer quantity) {
        orderMapper.updateInventory(productID, quantity);
    }


    @Override
    public List<OrderDetails> getOrderDetails(Integer id){
        return orderMapper.getOrderDetails(id);
    }

}
