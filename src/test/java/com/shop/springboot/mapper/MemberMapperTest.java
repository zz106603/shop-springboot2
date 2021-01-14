package com.shop.springboot.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.domain.NoticeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testOfInsert(){
        MemberDto params = new MemberDto();

        params.setMemberId("test");
        params.setMemberPw("test");
        params.setMemberName("test");
        params.setMemberEmail("test");
        params.setMemberAddr1("test");
        params.setMemberAddr2("test");
        params.setMemberAddr3("test");

        int result = memberMapper.insertMember(params);
        System.out.println("결과는 " + result + "입니다.");
    }



    @Test
    public void testOfSelectDetail(){
        MemberDto dto = memberMapper.selectMemberDetail((long)1);
        try{
            String json = new ObjectMapper().writeValueAsString(dto);
            System.out.println("================");
            System.out.println(json);
            System.out.println("================");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testOfUpdate(){
        MemberDto params = new MemberDto();

        params.setMemberId("test");
        params.setMemberPw("test2");
        params.setMemberName("test2");
        params.setMemberEmail("test2");
        params.setMemberAddr1("test2");
        params.setMemberAddr2("test2");
        params.setMemberAddr3("test2");

        int result = memberMapper.updateMember(params);
        if(result == 1){
            MemberDto memberDto = memberMapper.selectMemberDetail((long)1);
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

    @Test
    public void testOfDelete(){
        int result = memberMapper.deleteMember((long)1);
        if(result == 1){
            MemberDto dto = memberMapper.selectMemberDetail((long)1);
            try{
                String json = new ObjectMapper().writeValueAsString(dto);
                System.out.println("================");
                System.out.println(json);
                System.out.println("================");
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void idCheck(){
        String id = "zz";
        String id2 = "test";
        memberMapper.idCheck(id);
        memberMapper.idCheck(id2);
    }
}
