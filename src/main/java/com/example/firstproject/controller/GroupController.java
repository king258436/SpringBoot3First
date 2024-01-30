package com.example.firstproject.controller;

import com.example.firstproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/group/{groupId}")
    public String show(@PathVariable Long groupId, Model model){
        //1. id를 조회해 데이터 가져오기
        Group groupDto = groupService.getGroup(groupId);
        List<teamDto> teamDtos = teamService.getTeamsOnGroup(groupId);
        //2. 모델에 데이터 등록하기
        model.addAttribute("groupname", groupDto.getName());
        model.addAttribute("teamDtoList", teamDtos);
        //3. 뷰 페이지 반환하기
        return "groups/show"
    }
}
