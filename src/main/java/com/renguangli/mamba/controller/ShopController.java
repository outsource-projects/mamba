package com.renguangli.mamba.controller;

import com.renguangli.mamba.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ShopController
 *
 * @author renguangli 2018/2/12 2:32
 * @since JDK 1.8
 */
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public Map<String, Object> shopAnalysis(String shopType, String time, String search, String sort, String order, int offset, int limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("total",  shopService.countShopAnalysis(shopType, time));
        map.put("rows", shopService.shopAnalysis(shopType, time, search, offset, limit, sort, order));
        return map;
    }
}
