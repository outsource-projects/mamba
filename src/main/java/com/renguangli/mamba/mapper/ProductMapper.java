package com.renguangli.mamba.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import com.renguangli.mamba.entity.Product;

public interface ProductMapper {

    @SelectProvider(type = ProductProvider.class, method = "listProduct")
    List<Product> listProduct(Map<String, Object> map);

    @SelectProvider(type = ProductProvider.class, method = "countProduct")
    int countProduct(Map<String, Object> map);
    
}
