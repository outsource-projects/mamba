package com.renguangli.mamba.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ShopRepository
 *
 * @author renguangli 2018/2/12 2:51
 * @since JDK 1.8
 */
@Repository
public class ShopRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> shopAnalysis(String shopType, String time, String search, int pageNo, int pageSize, String sort, String order) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT SH.SHOP_URL,SH.SHOP_NAME,SUM(SALESCOUNT) TOTALSALESCOUNT,SUM(COLLECTIONCOUNT) TOTALCOLLECTIONCOUNT,SUM(SALES) TOTALSALES ");
        sql.append("FROM (SELECT S.SHOP_URL,S.SHOP_NAME, (ABS(MAX(S.SALES_COUNT)- MIN(S.SALES_COUNT))) SALESCOUNT,(ABS(MAX(S.COLLECTION_COUNT)- MIN(S.COLLECTION_COUNT))) COLLECTIONCOUNT, (MAX(S.SALES_COUNT)- MIN(S.SALES_COUNT)) * S.PRICE SALES FROM ");
        if ("my".equals(shopType)) {
            sql.append("SHOP_MY S ");
        } else if ("other".equals(shopType)) {
            sql.append("SHOP_OTHER S ");
        }
        if (time != null && !"".equals(time)) {
            String[] times = time.split("\\s-\\s");
            sql.append("WHERE S.TIME BETWEEN UNIX_TIMESTAMP('" + times[0] + "') AND UNIX_TIMESTAMP('" + times[1] + "') ");
        }
        sql.append("GROUP BY S.PID ) SH ");
        sql.append("WHERE 1=1 ");
        if (search != null && !"".equals(search)) {
            sql.append("SH.SHOP_NAME = '" + search + "' ");
        }

        sql.append("GROUP BY SH.SHOP_NAME ");
        if (sort != null && !"".equals(sort)) {
            sql.append("ORDER BY " + sort + " " + order.toUpperCase() + " ");
        }
        sql.append("LIMIT " + pageNo + "," + pageSize);
        return jdbcTemplate.queryForList(sql.toString());
    }

    public Integer countShopAnalysis(String shopType, String time) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM (SELECT SH.SHOP_NAME  FROM(SELECT S.SHOP_NAME FROM ");
        if ("my".equals(shopType)) {
            sql.append("SHOP_MY S ");
        } else if ("other".equals(shopType)) {
            sql.append("SHOP_OTHER S ");
        }
        if (time != null && !"".equals(time)) {
            String[] times = time.split("\\s-\\s");
            sql.append("WHERE S.TIME BETWEEN UNIX_TIMESTAMP('" + times[0] + "') AND UNIX_TIMESTAMP('" + times[1] + "') ");
        }
        sql.append("GROUP BY S.PID ) SH GROUP BY SH.SHOP_NAME) SHP ");
        return jdbcTemplate.queryForObject(sql.toString(), Integer.TYPE);
    }

    public List<Map<String, Object>> timeInterval(String shopType, String time) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CONCAT(FROM_UNIXTIME(MIN(S.TIME),'%Y-%m-%d %H:%i'),' ~ ',FROM_UNIXTIME(MAX(S.TIME),'%Y-%m-%d %H:%i')) TIME,(TO_DAYS(FROM_UNIXTIME(MAX(S.TIME),'%Y-%m-%d %H:%i')) - TO_DAYS(FROM_UNIXTIME(MIN(S.TIME),'%Y-%m-%d %H:%i'))) DAY , S.SHOP_NAME FROM ");
        if ("my".equals(shopType)) {
            sql.append("SHOP_MY S ");
        } else if ("other".equals(shopType)) {
            sql.append("SHOP_OTHER S ");
        }
        if (time != null && !"".equals(time)) {
            String[] times = time.split("\\s-\\s");
            sql.append("WHERE S.TIME BETWEEN UNIX_TIMESTAMP('" + times[0] + "') AND UNIX_TIMESTAMP('" + times[1] + "') ");
        }
        sql.append("GROUP BY S.SHOP_NAME ");
        return jdbcTemplate.queryForList(sql.toString());
    }
}
