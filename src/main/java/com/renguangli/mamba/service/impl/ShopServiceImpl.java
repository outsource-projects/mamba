package com.renguangli.mamba.service.impl;

import com.renguangli.mamba.repository.ShopRepository;
import com.renguangli.mamba.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ShopServiceImpl
 *
 * @author renguangli 2018/2/12 2:39
 * @since JDK 1.8
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Integer countShopAnalysis(String shopType, String time) {
        return shopRepository.countShopAnalysis(shopType, time);
    }

    @Override
    public List<Map<String, Object>> shopAnalysis(String shopType, String time, String search, int offset, int pageSize, String sort, String order) {
        List<Map<String, Object>> shopAnalysis = shopRepository.shopAnalysis(shopType, time, search, offset, pageSize, sort, order);
        List<Map<String, Object>> timeIntervals = shopRepository.timeInterval(shopType, time);
        for (Map<String, Object> map : shopAnalysis) {
           setValve(timeIntervals, map);
        }
        return shopAnalysis;
    }

    private void setValve(List<Map<String, Object>> timeIntervals, Map<String, Object> map) {
        for (Map<String, Object> timeInterval : timeIntervals) {
            if (map.get("SHOP_NAME").equals(map.get("SHOP_NAME"))) {
                map.put("TIME", timeInterval.get("TIME"));

                int day = Integer.parseInt(timeInterval.get("DAY").toString());
                Double totalSalesCount = Double.parseDouble(map.get("TOTALSALESCOUNT").toString());
                map.put("AVGSALESCOUNT", totalSalesCount/day);

                Double totalSales = Double.parseDouble(map.get("TOTALSALES").toString());
                map.put("AVGSALES", totalSales/day);

                Double totalCollectionCount = Double.parseDouble(map.get("TOTALCOLLECTIONCOUNT").toString());
                map.put("AVGCOLLECTIONCOUNT", totalCollectionCount/day);
            }
        }
    }
}
