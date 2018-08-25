package com.renguangli.mamba.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renguangli.mamba.entity.KuaiDi;
import com.renguangli.mamba.entity.KuaiDiDir;
import com.renguangli.mamba.service.KuaiDiDirService;
import com.renguangli.mamba.service.KuaiDiService;
import com.renguangli.mamba.util.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * OrderController
 *
 * @author renguangli 2018/2/27 20:19
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Value("${kdn.api.url}")
    private String url;

    @Value("${kdn.api.appId}")
    private String appId;

    @Value("${kdn.api.appKey}")
    private String appKey;

    @Autowired
    private KuaiDiService kuaiDiService;

    @Autowired
    private KuaiDiDirService kuaiDiDirService;

    @GetMapping({"", "/"})
    public String order() {
        /*restTemplate.getForObject(url, String.class, )*/
        return "order";
    }

    @ResponseBody
    @PostMapping("/file")
    public boolean uploadFile(@RequestParam(name = "excel") MultipartFile file) {
        try {
            setKuaiDiCode(file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @ResponseBody
    @GetMapping("/get")
    public Map<String, Object> listOrder(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo - 1, pageSize);
        Page<KuaiDi> page = kuaiDiService.kuaiDiList(pageable);
        List<KuaiDi> kuaiDiList = getLogisticsDetails(page.getContent());
        Map<String, Object> map = new HashMap<>();
        map.put("kuaiDiList", kuaiDiList);
        map.put("totalCounts", page.getTotalElements());
        return map;
    }

    private List<KuaiDi> getLogisticsDetails(List<KuaiDi> kuaiDiList) {
        CountDownLatch latch = new CountDownLatch(kuaiDiList.size());
        for (KuaiDi kuaiDi : kuaiDiList) {
            new Thread(() -> {
                try {
                    callApi(kuaiDi);
                    latch.countDown();
                } catch (Exception e) {
                    log.error("物流接口调用失败：", e);
                }
            }).start();
        }
        try {
            latch.await(8, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return kuaiDiList;
    }

    private void callApi(KuaiDi kuaiDi) {
        String responseDate = ApiUtil.getOrderTraces(url, appId, appKey, kuaiDi.getCode(), kuaiDi.getNo());
        JSONObject jsonObject = JSON.parseObject(responseDate);
        String success = jsonObject.getString("Success");
        if ("true".equals(success)) {
            kuaiDi.setState(jsonObject.getInteger("State"));
            JSONArray data = jsonObject.getJSONArray("Traces");
            if (data.size() > 0) {
                kuaiDi.setData(data.toJSONString());
                Long fromTime = data.getJSONObject(0).getDate("AcceptTime").getTime();
                Long time = data.getJSONObject(data.size() - 1).getDate("AcceptTime").getTime();
                double intervalHour = (time - fromTime) / (60 * 60 * 1000);
                kuaiDi.setIntervalHour((int) intervalHour);
                if (intervalHour > 24) {
                    kuaiDi.setClassify(1);
                } else {
                    kuaiDi.setClassify(0);
                }
            } else {
                kuaiDi.setIntervalHour(0);
                kuaiDi.setClassify(0);
                kuaiDi.setData(jsonObject.getString("Reason"));
            }
        } else {
            kuaiDi.setData(success);
        }
    }

    private void setKuaiDiCode(@RequestParam(name = "excel") MultipartFile file) {
        List<KuaiDi> kuaiDiList = readExcel(file);
        if (kuaiDiList != null) {
            Date date = new Date();
            for (KuaiDi kuaiDi : kuaiDiList) {
                for (KuaiDiDir kuaiDiDir : kuaiDiDirService.kuaiDiDirList()) {
                    if (kuaiDiDir.getName().contains(kuaiDi.getName())) {
                        kuaiDi.setCode(kuaiDiDir.getCode());
                        kuaiDi.setDate(date);
                    }
                }
            }
        }
        kuaiDiService.save(kuaiDiList);
    }

    private List<KuaiDi> readExcel(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            return reader.readAll(KuaiDi.class);
        } catch (Exception e) {
            log.info("failed to read excel.");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
