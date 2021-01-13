package com.shop.springboot.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class NoticeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 글쓰기페이지_로딩(){
        String body = this.restTemplate.getForObject("/notice/write.do", String.class);

        assertThat(body).contains("하이");
    }

    @Test
    public void 메인페이지_로딩(){
        String body = this.restTemplate.getForObject("/notice/main.do", String.class);

        assertThat(body).contains("스프링");
    }


}
