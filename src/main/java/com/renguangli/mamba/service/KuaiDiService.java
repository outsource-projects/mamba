package com.renguangli.mamba.service;

import com.renguangli.mamba.entity.KuaiDi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * KuaiDiService
 *
 * @author renguangli 2018/2/27 21:26
 * @since JDK 1.8
 */
public interface KuaiDiService {

    Page<KuaiDi> kuaiDiList(Pageable pageable);

    void save(List<KuaiDi> kuaiDis);
}
