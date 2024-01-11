package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// 해당 클래스가 컨트롤러임을 선언
public class FirstController {
    public String name = "자바촙5";
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){

        model.addAttribute("username",name);
        model.addAttribute("username2","나는 초보2");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYou(Model model){
        model.addAttribute("username",name);
        return "goodbye";
    }
}
