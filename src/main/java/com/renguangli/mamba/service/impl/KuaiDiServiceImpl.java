package com.renguangli.mamba.service.impl;

import com.renguangli.mamba.entity.KuaiDi;
import com.renguangli.mamba.repository.KuaiDiRepository;
import com.renguangli.mamba.service.KuaiDiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * KuaiDiServiceImpl
 *
 * @author renguangli 2018/2/27 21:27
 * @since JDK 1.8
 */
@Service
public class KuaiDiServiceImpl implements KuaiDiService {

    @Autowired
    private KuaiDiRepository kuaiDiRepository;

    @Override
    public Page<KuaiDi> kuaiDiList(Pageable pageable) {
        return kuaiDiRepository.findAll(pageable);
    }

    @Override
    public void save(List<KuaiDi> kuaiDis) {
        kuaiDiRepository.save(kuaiDis);
    }
}
