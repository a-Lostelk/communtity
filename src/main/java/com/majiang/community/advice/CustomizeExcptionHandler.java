package com.majiang.community.advice;

import com.alibaba.fastjson.JSON;
import com.majiang.community.dto.ResultDTO;
import com.majiang.community.exception.CustomizeErrorCode;
import com.majiang.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable throwable, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            if (throwable instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) throwable);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        } else {
            //判断异常是否属于CustomizeException自定义的枚举异常，并返回model结果到页面
            if (throwable instanceof CustomizeException) {
                model.addAttribute("message", throwable.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("/error");
        }
    }
}
