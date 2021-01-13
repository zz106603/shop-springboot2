package com.shop.springboot.mapper;

import com.shop.springboot.domain.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper{
    public int insertNotice(NoticeDto params);
    public NoticeDto selectNoticeDetail(Long idx);
    public int updateNotice(NoticeDto params);
    public int deleteNotice(Long idx);
    public List<NoticeDto> selectNoticeList();
    public int selectNoticeTotalCount();
}
