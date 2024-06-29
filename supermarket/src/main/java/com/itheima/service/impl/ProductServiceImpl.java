package com.itheima.service.impl;


import com.itheima.mapper.ProductMapper;
import com.itheima.pojo.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list(){
        List<Product> productList = productMapper.list();
        return productList;
    }

    //添加商品信息
    @Override
    public void addProduct( Product product){
        productMapper.addProduct(product);
    }

    //更新商品信息
    @Override
    public void updata(Product product){
        productMapper.updata(product);
    }

    //删除商品信息
    @Override
    public void delete(Integer id){
        productMapper.deleteByID(id);
    }

}
