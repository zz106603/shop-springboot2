package com.shop.springboot.service.notice;

import com.shop.springboot.domain.NoticeDto;

import java.util.List;

public interface NoticeService {
    public boolean registerNotice(NoticeDto params);
    public NoticeDto getNoticeDetail(Long idx);
    public boolean deleteNotice(Long idx);
    public List<NoticeDto> getBoardList();
}
