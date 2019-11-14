package com.example.demo11.controller;

import com.example.demo11.dto.QuestionDTO;
import com.example.demo11.mapper.QuestMapper;
import com.example.demo11.model.User;
import com.example.demo11.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable("id") Integer id, Model model, HttpServletRequest request){
        QuestionDTO questionDTO = questionService.getById(id);
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getId());
        System.out.println(questionDTO.getCreator());
        model.addAttribute("question",questionDTO);

        return "question";
    }

}
