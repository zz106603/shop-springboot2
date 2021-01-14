package com.shop.springboot.controller.member;

import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.service.member.MemberService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@NoArgsConstructor
public class MemberController {

    private MemberService memberService;

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
