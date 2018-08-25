package com.renguangli.mamba.service;

import java.util.List;
import java.util.Map;

import com.renguangli.mamba.entity.Pager;
import com.renguangli.mamba.entity.Product;

/**
 * ProductService
 *
 * @author renguangli 2018/1/21 0:30
 * @since JDK 1.8
 */
public interface ProductService {

    List<Product> listProduct(String search, String time, String tableName, Pager pager);
    
    int countProduct(String search, String time, String tableName);

    List<Product> save(List<Product> products);

    List<Map<String, Object>> productAnalysis(String search, String time,int pageNo, int pageSize, String sort, String order);

    Integer countProductAnalysis(String search, String time);

    Product findByPid(String pid);
}
