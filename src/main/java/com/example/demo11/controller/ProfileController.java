package com.example.demo11.controller;

import com.example.demo11.dto.PaginationDTO;
import com.example.demo11.model.User;
import com.example.demo11.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("profile/{action}")
    public String profile(@PathVariable(name="action") String action, Model model,
                          HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.myList(user.getId());
            model.addAttribute("pagination",paginationDTO);
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
