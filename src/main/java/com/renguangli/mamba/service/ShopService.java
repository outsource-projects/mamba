package com.renguangli.mamba.service;

import java.util.List;
import java.util.Map;

/**
 * ShopService
 *
 * @author renguangli 2018/2/12 2:37
 * @since JDK 1.8
 */
public interface ShopService {

    Integer countShopAnalysis(String shopType, String time);

    List<Map<String, Object>> shopAnalysis(String shopType, String time, String search, int offset, int pageSize, String sort, String order);
}
