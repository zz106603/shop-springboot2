package com.shop.springboot.controller.member;

import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member/join")
    public Long save(@RequestBody MemberDto memberDto){
        boolean temp = memberService.registerMember(memberDto);
        long result = 0;
        if(temp == true){
            result = 1;
        }else{
            result = 0;
        }
        return result;
    }
    @PutMapping("/api/v1/member/join/{idx}")
    public Long update(@PathVariable Long idx,
                       @RequestBody MemberDto memberDto){

        memberDto.setIdx(idx);
        boolean temp = memberService.registerMember(memberDto);
        long result = 0;
        if(temp == true){
            result = 1;
        }else{
            result = 0;
        }
        return result;
    }

    @DeleteMapping("/api/v1/member/join/{idx}")
    public Long delete(@PathVariable Long idx){
        boolean temp = memberService.deleteMember(idx);
        long result = 0;
        if(temp == true){
            result = 1;
        }else{
            result = 0;
        }
        return result;
    }

    @PostMapping("/api/v1/member/join/IdCheck")
    public String checkId(String memberId){
        int result = memberService.idCheck(memberId);

        if(result != 0){
            return "fail";
        }else{
            return "success";
        }
    }

    @PostMapping("/api/v1/member/join/EmailCheck")
    public String checkEmail(String memberEmail){
        int result = memberService.emailCheck(memberEmail);

        if(result != 0){
            return "fail";
        }else{
            return "success";
        }
    }


}
