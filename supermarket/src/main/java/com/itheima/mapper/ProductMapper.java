package com.itheima.mapper;

import com.itheima.pojo.Product;
import com.itheima.service.ProductService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    //查询所有商品信息
    @Select("select * from product")
    List<Product> list();


    //添加商品信息
    @Insert("insert into product (productName, price, stock) value (#{productName},#{price},#{stock})")
    void addProduct(Product product);

    //修改商品信息
    @Update("update product set productName = #{productName}, price = #{price}, stock = #{stock} where productID = #{productID}")
    void updata(Product product);

    //删除商品信息
    @Delete("delete from product where productID = #{id}")
    void deleteByID(Integer id);
}
