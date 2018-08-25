package com.renguangli.mamba.service.impl;

import com.renguangli.mamba.entity.KuaiDiDir;
import com.renguangli.mamba.repository.KuaiDiDirRepository;
import com.renguangli.mamba.service.KuaiDiDirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * KuaiDiServiceImpl
 *
 * @author renguangli 2018/2/27 21:27
 * @since JDK 1.8
 */
@Service
public class KuaiDiDirServiceImpl implements KuaiDiDirService {

    @Autowired
    private KuaiDiDirRepository kuaiDiDirRepository;

    @Override
    public List<KuaiDiDir> kuaiDiDirList() {
        return kuaiDiDirRepository.findAll();
    }

    @Override
    public List<KuaiDiDir> kuaiDiDList() {
        System.out.println(2323);
        return null;
    }

}
