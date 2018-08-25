package com.renguangli.mamba.repository;


import com.renguangli.mamba.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * ProductRepository
 *
 * @author renguangli 2018/1/21 0:28
 * @since JDK 1.8
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    Product findByPidAndTimeLessThan(String pid, Date time);

}
