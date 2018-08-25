package com.renguangli.mamba.controller;

import com.renguangli.mamba.entity.User;
import com.renguangli.mamba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * LoginController
 *
 * @author renguangli 2018/2/11 23:26
 * @since JDK 1.8
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(Model model, HttpSession session, String username, String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "用户名不存在！");
            return "login";
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "请输入正确的密码！");
            return "login";
        }
        session.setAttribute("SYS_USER", user);
        return "redirect:/pageView?pageId=1";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("SYS_USER");
        return "redirect:/";
    }
}
