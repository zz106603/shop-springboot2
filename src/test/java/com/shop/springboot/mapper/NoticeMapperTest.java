package com.shop.springboot.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.mapper.NoticeMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
class MapperTests {

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    public void testOfInsert() {
        NoticeDto params = new NoticeDto();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");

        int result = noticeMapper.insertNotice(params);
        System.out.println("결과는 " + result + "입니다.");
    }

    @Test
    public void testOfSelectDetail(){
        NoticeDto dto = noticeMapper.selectNoticeDetail((long)1);
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
        NoticeDto dto = new NoticeDto();
        dto.setTitle("1번 제목 수정");
        dto.setContent("1번 게시글 수정");
        dto.setWriter("홍길동");
        dto.setIdx((long)1);

        int result = noticeMapper.updateNotice(dto);
        if(result == 1){
            NoticeDto noticeDto = noticeMapper.selectNoticeDetail((long)1);
            try{
                String json = new ObjectMapper().writeValueAsString(noticeDto);
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
        int result = noticeMapper.deleteNotice((long)1);
        if(result == 1){
            NoticeDto dto = noticeMapper.selectNoticeDetail((long)1);
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
    public void testMultipleInsert(){
        for(int i=2; i<=30; i++){
            NoticeDto dto = new NoticeDto();
            dto.setTitle(i+ "번 게시글 제목");
            dto.setContent(i+"번 게시글 내용");
            dto.setWriter(i+"번 게시글 작성자");
            noticeMapper.insertNotice(dto);
        }
    }

    @Test
    public void testSelectList(){
        int noticeTotalCount = noticeMapper.selectNoticeTotalCount();
        if(noticeTotalCount > 0){
            List<NoticeDto> list = noticeMapper.selectNoticeList();
            System.out.println(list);
            if(CollectionUtils.isEmpty(list) == false){
                for(NoticeDto dto : list){
                    System.out.println("==============");
                    System.out.println(dto.getTitle());
                    System.out.println(dto.getContent());
                    System.out.println(dto.getWriter());
                    System.out.println("==============");
                }
            }
        }
    }

}