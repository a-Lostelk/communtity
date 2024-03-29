package com.majiang.community.controller;

import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/23
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        //增加阅读数
//        questionService.incView(id);
        model.addAttribute("questionDTO", questionDTO);
        return "question";
    }
}
