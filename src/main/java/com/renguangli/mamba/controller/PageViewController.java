package com.renguangli.mamba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageViewController
 *
 * @author renguangli 2018/3/11 17:53
 * @since JDK 1.8
 */
@Controller
public class PageViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/pageView")
    public String pageView(String pageId) {
        if ("2".equals(pageId)) {
            return "product/analysis";
        } else if ("3".equals(pageId)) {
            return "shop/index";
        } else if ("4".equals(pageId)) {
            return "shop/analysis";
        } else if ("5".equals(pageId)) {
            return "shop/analysis-other";
        } else if ("6".equals(pageId)) {
            return "product/new";
        } else {
            return "product/index";
        }
    }
}
