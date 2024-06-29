package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/product/getByID")
    public Result getProductByID(@RequestBody Map<String ,Integer> request){
        Integer productID = request.get("productID");
        Product getProduct = orderService.getProductByID(productID);
//        System.out.println("测试"+id.getProductID());
        return Result.success(getProduct);
    }
    @PostMapping("/member/getByID")
    public Result getMemberByID(@RequestBody Map<String ,Integer> request){
        Integer memberID = request.get("memberID");
        Member getMember = orderService.getMemberByID(memberID);
//        System.out.println("测试"+memberID);
        return Result.success(getMember);
    }

    @PostMapping("/order/get")
    public Result getOrderDetails(@RequestBody Map<String ,Integer> request){
        Integer orderID = request.get("orderID");
        List<OrderDetails> orderDetails = orderService.getOrderDetails(orderID);
        return Result.success(orderDetails);
    }

    @PostMapping("/product/updata")
    public Result updateInventory(@RequestBody UpdateInventoryRequest request) {
        orderService.updateInventory(request.getProductID(), request.getQuantity());
        return Result.success();
    }

    @PostMapping("/order/getOrderID")
    public Result addOrder(@RequestBody Order order){
        Integer orderID = orderService.addOrder(order);
        return Result.success(orderID);
    }
    @PostMapping("/orderDetails")
    public Result addOrderDetail(@RequestBody OrderDetails orderDetails){
        orderService.addOrderDetail(orderDetails);
        return Result.success();
    }


}
