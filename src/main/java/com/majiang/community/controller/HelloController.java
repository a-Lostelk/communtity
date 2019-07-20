package com.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/20
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
