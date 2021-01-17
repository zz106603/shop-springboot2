package com.shop.springboot.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.service.member.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join.do")
    public String openJoinWrite(@RequestParam(value = "idx", required = false) Long idx, Model model){

        if(idx == null){
            model.addAttribute("member", new MemberDto());
        }else{
            MemberDto dto = memberService.getMemberDetail(idx);
            if(dto == null){
                return "redirect:/";
            }
            model.addAttribute("member", dto);
        }
        return "member/join";
    }

    @GetMapping("/member/login.do")
    public String openLogin(){
        return "member/login";
    }

    @PostMapping("/member/login.do")
    public String loginPost(HttpServletRequest request, MemberDto memberDto, RedirectAttributes rttr) throws Exception{

        boolean result = false;
        HttpSession session = request.getSession();
        MemberDto lvo = memberService.memberLogin(memberDto);

        if(lvo == null){
            result = true;
            rttr.addFlashAttribute("result", result);
            return "redirect:/member/login.do";
        }
            session.setAttribute("member", lvo);

        return "redirect:/notice/main.do";
    }


    @GetMapping("/member/update/{idx}")
    public String memberUpdate(@PathVariable Long idx, Model model){

        if(idx == null){
            //올바르지 않은 접근
            return "redirect:/";
        }
        MemberDto memberDto = memberService.getMemberDetail(idx);
        if(memberDto == null || "Y".equals(memberDto.getDeleteYn())){
            //없는 회원이거나 이미 탈퇴한
            return "redirect:/";
        }
        model.addAttribute("memberDto", memberDto);

        return "member/join-update";
    }

    @GetMapping("/member/read/{idx}")
    public String noticeRead(@PathVariable Long idx, Model model){

        if(idx == null){
            //올바르지않은 접근
            return "redirect:/";
        }
        MemberDto memberDto = memberService.getMemberDetail(idx);
        if(memberDto == null || "Y".equals(memberDto.getDeleteYn())){
            //없는 회원이거나 이미 탈퇴한 회원
            return "redirect:/";
        }
        model.addAttribute("memberDto", memberDto);

        return "member/join-read";

    }

}

