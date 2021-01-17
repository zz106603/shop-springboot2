package com.shop.springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void login_mapper_test() throws Exception{
        MemberDto dto = new MemberDto();
        dto.setMemberId("zz");
        dto.setMemberPw("zz");

        try{
            String json = new ObjectMapper().writeValueAsString(dto);
            System.out.println("================");
            System.out.println(json);
            System.out.println("================");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        MemberDto memberDto = memberService.memberLogin(dto);
        if(memberDto == null){
            System.out.println("null이다!!!!!!!!!!!!!!!!!!!!!!!");
        }else{
            System.out.println("null이 아니다!!!!!!!!!!!!!!!!!!!!!!!");
        }

        try{
            String json = new ObjectMapper().writeValueAsString(memberDto);
            System.out.println("================");
            System.out.println(json);
            System.out.println("================");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
