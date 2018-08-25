package com.renguangli.mamba.mapper;

import java.util.Map;

import com.renguangli.mamba.entity.Pager;
import com.renguangli.mamba.util.StrUtils;

public class ProductProvider {

    public String listProduct(Map<String, Object> map) {
    	String search = (String) map.get("search");
    	String time = (String) map.get("time");
    	String tableName = (String) map.get("tableName");
    	Pager pager = (Pager) map.get("pager");
        StringBuilder sql = new StringBuilder();
        sql.append("select p.xid,p.url,from_unixtime(p.time) time,p.pid,p.name,p.brand,p.top_brand topBrand,p.price,");
        sql.append("p.original_price originalPrice,sales_count salesCount,collection_count collectionCount,");
        sql.append("p.stock,p.score,p.comments_count commentsCount,p.product_url productUrl,p.sid,");
        sql.append("p.shop_name shopName,p.shop_url shopUrl,p.keyword,p.keyword_rank keyworRrank ");
        sql.append("from " + tableName + " p ");
        sql.append("where 1=1 ");
        if (StrUtils.isNotEmpty(search)) {
			sql.append("and p.shop_name like '%" + search + "%' ");
		}
        if (StrUtils.isNotEmpty(time)) {
        	String[] times = time.split("\\s-\\s");
            sql.append("and from_unixtime(p.time) between '" + times[0] + "' and '" + times[1] + "' ");
		}
        if (StrUtils.isNotEmpty(pager.getSort())) {
			sql.append("order by " + pager.getSort() + " " + pager.getOrder() + " ");
		}
        sql.append("limit " + pager.getOffset() + "," + pager.getLimit());
        return sql.toString();
    }
    
    public String countProduct(Map<String, Object> map) {
    	String search = (String) map.get("search");
    	String time = (String) map.get("time");
    	String tableName = (String) map.get("tableName");
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) ");
        sql.append("from " + tableName + " p ");
        sql.append("where 1=1 ");
        if (StrUtils.isNotEmpty(search)) {
			sql.append("and p.shop_name = '%" + search + "%' ");
		}
        if (StrUtils.isNotEmpty(time)) {
        	String[] times = time.split("\\s-\\s");
            sql.append("and from_unixtime(p.time) between '" + times[0] + "' and '" + times[1] + "' ");
		}
        return sql.toString();
    }
}
