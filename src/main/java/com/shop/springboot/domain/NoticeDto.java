package com.shop.springboot.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class NoticeDto {
    private final Long idx;
    private final String title;
    private final String content;
    private final String writer;
    private final int viewCnt;
    private final String noticeYn;
    private final String deleteYn;
    private final LocalDateTime insertTime;
    private final LocalDateTime updateTime;
    private final LocalDateTime deleteTime;
}
