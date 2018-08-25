package com.renguangli.mamba.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.renguangli.mamba.service.KuaiDiService;

@Component
public class OrderTask {

    @Autowired
    private KuaiDiService kuaiDiService;

    /**
     * 每日12点删除5天之前的运单
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void deleteOrder() {
    	kuaiDiService.hashCode();
    }
}
