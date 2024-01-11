package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/say")
    public String wiseSaying(Model model) {
        String str[] = {
                "삶이 있는 한 희망은 있다 "
                        + "-키케로",
                "산다는것 그것은 치열한 전투이다. "
                        + "-로망로랑",
                "하루에 3시간을 걸으면 7년 후에 지구를 한바퀴 돌 수 있다." +
                        "-사무엘존슨",
                "언제나 현재에 집중할수 있다면 행복할것이다." +
                        "-파울로 코엘료",
                "진정으로 웃으려면 고통을 참아야하며 , 나아가 고통을 즐길 줄 알아야 해" +
                        "-찰리 채플린"
        };
        int randInt = (int) (Math.random() * str.length);
        model.addAttribute("randomQuote", str[randInt]);
        return "wiseSaying";
    }
}
