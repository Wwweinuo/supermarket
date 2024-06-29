package com.itheima.controller;

import com.itheima.pojo.Product;
import com.itheima.pojo.Result;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/get")
    public Result list(){
        List<Product> productList = productService.list();
        return Result.success(productList);
    }

    @PostMapping("/product/add")
    public Result addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return Result.success();
    }

    @PostMapping("product/up")
    public Result updata(@RequestBody Product product){
        productService.updata(product);
        return Result.success();
    }

    @DeleteMapping("/product/delete")
    public Result delete(@RequestParam Integer productID){
        productService.delete(productID);
        return Result.success();
    }



}
