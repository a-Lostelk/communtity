package com.majiang.community.controller;

import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/20
 */
@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.findUserByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
