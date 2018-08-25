package com.renguangli.mamba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renguangli.mamba.entity.Pager;
import com.renguangli.mamba.entity.Product;
import com.renguangli.mamba.service.ProductService;

/**
 * ProductController
 *
 * @author renguangli 2018/1/20 16:38
 * @since JDK 1.8
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Map<String, Object> listProduct(String search, String time, String tableName, Pager pager) {
        Map<String, Object> map = new HashMap<>();
        List<Product> listProduct = productService.listProduct(search, time, tableName, pager);
        map.put("rows", listProduct);
        map.put("total", productService.countProduct(search, time, tableName));
        return map;
    }

    @GetMapping("/products/analysis")
    public Map<String, Object> productAnalysis(String search,String time, String sort, String order, int offset, int limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("total",  productService.countProductAnalysis(search, time));
        map.put("rows", productService.productAnalysis(search, time, offset, limit, sort, order));
        return map;
    }

}
