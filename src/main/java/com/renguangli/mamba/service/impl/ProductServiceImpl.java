package com.renguangli.mamba.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.renguangli.mamba.entity.Pager;
import com.renguangli.mamba.entity.Product;
import com.renguangli.mamba.mapper.ProductMapper;
import com.renguangli.mamba.repository.ProductRepository;
import com.renguangli.mamba.service.ProductService;
import com.renguangli.mamba.util.StrUtils;

/**
 * ProductServiceImpl
 *
 * @author renguangli 2018/1/21 0:41
 * @since JDK 1.8
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private ProductMapper productMapper;

    /* (non-Javadoc)
	 * @see com.renguangli.mamba.service.ProductService#listProduct(java.lang.String, com.renguangli.mamba.entity.Pager)
	 */
	@Override
	public List<Product> listProduct(String search, String time, String tableName, Pager pager) {
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("time", time);
		map.put("tableName", tableName);
		map.put("pager", pager);
		return productMapper.listProduct(map);
	}

	/* (non-Javadoc)
	 * @see com.renguangli.mamba.service.ProductService#countProduct(java.lang.String)
	 */
	@Override
	public int countProduct(String search, String tableName, String time) {
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("time", time);
		map.put("tableName", tableName);
		return productMapper.countProduct(map);
	}

    @Override
    public List<Map<String, Object>> productAnalysis(String search, String time, int pageNo, int pageSize, String sort, String order) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CONCAT(FROM_UNIXTIME(MIN(P.TIME),'%Y-%m-%d %H:%i'),' ~ ',FROM_UNIXTIME(MAX(P.TIME),'%Y-%m-%d %H:%i')) TIME, ");
        sql.append("P.NAME,P.PID,P.PRODUCT_URL,P.SHOP_URL,P.SHOP_NAME,P.PRICE, ");
        sql.append("(ABS(MAX(P.SALES_COUNT) - MIN(P.SALES_COUNT)))/(TO_DAYS(FROM_UNIXTIME(MAX(P.TIME),'%Y-%m-%d %H:%i')) - TO_DAYS(FROM_UNIXTIME(MIN(P.TIME),'%Y-%m-%d %H:%i'))) + 0 SALESCOUNT, ");
        sql.append("(ABS(MAX(P.COLLECTION_COUNT) - MIN(P.COLLECTION_COUNT)))/(TO_DAYS(FROM_UNIXTIME(MAX(P.TIME),'%Y-%m-%d %H:%i')) - TO_DAYS(FROM_UNIXTIME(MIN(P.TIME),'%Y-%m-%d %H:%i'))) + 0 COLLECTIONCOUNT, ");
        sql.append("(ABS(MAX(P.SALES_COUNT) - MIN(P.SALES_COUNT)))/(TO_DAYS(FROM_UNIXTIME(MAX(P.TIME),'%Y-%m-%d %H:%i')) - TO_DAYS(FROM_UNIXTIME(MIN(P.TIME),'%Y-%m-%d %H:%i'))) * P.PRICE + 0 SALES ");
        sql.append("FROM PRODUCT P WHERE 1=1 ");
        if (search != null && !"".equals(search)) {
            sql.append("AND P.NAME LIKE '%" + search + "%' ");
//            sql.append("OR P.SHOP_NAME LIKE '%" + search + "%' ");

        }
        if (StrUtils.isNotEmpty(time)) {
        	String[] times = time.split("\\s-\\s");
            sql.append("AND FROM_UNIXTIME(P.TIME) BETWEEN '" + times[0] + "' AND '" + times[1] + "' ");
		}
        sql.append("GROUP BY P.PID ");
        if (sort != null && !"".equals(sort)) {
            sql.append("ORDER BY " + sort + " " + order.toUpperCase() + " ");
        }
        sql.append("LIMIT " + pageNo + "," + pageSize);
        return jdbcTemplate.queryForList(sql.toString());
    }

    @Override
    public Integer countProductAnalysis(String search, String time) {
        String sql = "SELECT COUNT(*) FROM (SELECT P.NAME FROM PRODUCT P WHERE 1=1 ";
        if (search != null && !"".equals(search)) {
        	sql += "AND P.NAME LIKE '%" + search + "%' ";
//            sql += "OR P.SHOP_NAME LIKE '%" + search + "%' ";

        }
        if (StrUtils.isNotEmpty(time)) {
        	String[] times = time.split("\\s-\\s");
            sql += "AND FROM_UNIXTIME(P.TIME) BETWEEN '" + times[0] + "' AND '" + times[1] + "' ";
		}
        sql += "GROUP BY P.PID) P";
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    @Override
    public Product findByPid(String pid) {
        return productRepository.findByPidAndTimeLessThan(pid, new Date(new Date().getTime() - 1000*3600*24));
    }

    @Override
    public List<Product> save(List<Product> products) {
        return productRepository.save(products);
    }

}
