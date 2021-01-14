package com.shop.springboot.controller.member;

import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
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
    @PutMapping("/api/v1/member/{idx}")
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

    @DeleteMapping("/api/v1/member/{idx}")
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

    @PostMapping("/api/v1/member/IdCheck")
    public String checkId(String memberId){
        int result = memberService.idCheck(memberId);

        if(result != 0){
            return "fail";
        }else{
            return "success";
        }
    }
}
