package com.itheima.service;

import com.itheima.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    /**
     * 显示所有商品信息
     * @return
     */
    List<Product> list();

    /**
     * 添加商品信息
     * @param product
     */
    void addProduct(Product product);

    /**
     * 更新商品信息
     * @param product
     */
    void updata(Product product);

    /**
     * 删除商品信息
     * @param id
     */
    void delete(Integer id);
}
