package com.example.demo11.controller;

import com.example.demo11.mapper.QuestMapper;
import com.example.demo11.model.Question;
import com.example.demo11.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestMapper questMapper;

    @RequestMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/doPublish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());

        if (question.getTitle() == null || question.getTitle() == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (question.getDescription() == null || question.getDescription() == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (question.getTag() == null || question.getTag() == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questMapper.create(question);
        return "redirect:/";
    }

}
