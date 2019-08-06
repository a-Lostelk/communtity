package com.majiang.community.advice;

import com.majiang.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/6
 *
 * 异常处理拦截器
 */
@ControllerAdvice
public class CustomizeExcptionHandler {

    /**
     * Throwable 类是 Java 语言中所有错误或异常的父类
     * @param throwable
     * @return
     */
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable throwable, Model model) {
        //判断异常是否属于CustomizeException自定义的枚举异常，并返回model结果到页面
        if (throwable instanceof CustomizeException) {
            model.addAttribute("message", throwable.getMessage());
        }else {
            model.addAttribute("message","服务器冒烟了......");
        }
        return new ModelAndView("/error");
    }
}
