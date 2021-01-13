package com.shop.springboot.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
public class NoticeDtoTest {


    @Test
    public void 롬복_테스트() throws Exception{

         long idx = 1;
         String title = "test";
         String content = "test";
         String writer = "test";
         int viewCnt = 3;
         String noticeYn = "test";
         String deleteYn = "test";
         LocalDateTime insertTime = LocalDateTime.now();
         LocalDateTime updateTime = LocalDateTime.now();
         LocalDateTime deleteTime = LocalDateTime.now();

         NoticeDto dto = new NoticeDto(idx, title,content, writer,viewCnt, noticeYn, deleteYn, insertTime,updateTime, deleteTime);

         assertThat(dto.getContent()).isEqualTo(content);
        assertThat(dto.getWriter()).isEqualTo(writer);
        assertThat(dto.getTitle()).isEqualTo(title);

    }
}
