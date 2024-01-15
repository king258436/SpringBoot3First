package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/signup")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form){// DTO 생성
        log.info(form.toString());
        //1.DTO를 엔티티로 변환
        Member member = form.toEntity();
        log.info(member.toString());
        //2.리파지터리로 엔티티를 DB에 저장.
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return"redirect:/members/"+saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        // id를 조회해서 데이터가져오기
        Member memberEntity=memberRepository.findById(id).orElse(null);
        // 모델에 데이터 넣기
        model.addAttribute("member",memberEntity);
        // 뷰 페이지에 데이터 반환
        return "members/show";
    }
    @GetMapping("/members")
    public String index(Model model){
        // 데이터를 리스트째로 가져오기
        ArrayList<Member> memberEntityList=memberRepository.findAll();
        // 모델에 리스트 넣기
        model.addAttribute("memberList",memberEntityList);
        // 뷰 페이지에 데이터 반환
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("member",memberEntity);
        // 뷰 페이지 설정
        return "members/edit";
    }
    @PostMapping("/members/update")
    public String update(MemberForm form){
        //1. DTO를 엔티티로 변경
        Member memberEntity = form.toEntity();
        //2. 엔티티를 DB에 저장
        // 2-1 DB에서 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 2-2 기존 데이터 값을 갱신하기
        if(target!=null){
            memberRepository.save(memberEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //1. 삭제할 대상 가저오기
        Member target = memberRepository.findById(id).orElse(null);
        //2. 대상 엔티티 삭제하기
        if(target!=null){
            memberRepository.delete(target);
        }
        rttr.addFlashAttribute("msg","삭제됐습니다.");
        //3. 결과 페이지로 리다이렉트
        return "redirect:/members";
    }

}
