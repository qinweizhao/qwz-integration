package com.qinweizhao.freemarker.controller;

import com.qinweizhao.freemarker.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qinweizhao
 * @since 2022/3/30
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject(user);
        mv.setViewName("redirect:/");
        request.getSession().setAttribute("loginUser", user);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }
}
