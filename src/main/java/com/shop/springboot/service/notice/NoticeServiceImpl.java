package com.shop.springboot.service.notice;

import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    @Override
    public boolean registerNotice(NoticeDto params) {
        int queryResult = 0;
        if(params.getIdx() == null){
            queryResult = noticeMapper.insertNotice(params);
        }else{
            queryResult = noticeMapper.updateNotice(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public NoticeDto getNoticeDetail(Long idx) {
        return noticeMapper.selectNoticeDetail(idx);
    }

    @Override
    public boolean deleteNotice(Long idx) {
        int queryResult = 0;

        NoticeDto dto = noticeMapper.selectNoticeDetail(idx);

        if(dto != null && "N".equals(dto.getDeleteYn())){
            queryResult = noticeMapper.deleteNotice(idx);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<NoticeDto> getBoardList() {
        List<NoticeDto> list = Collections.emptyList();
        int noticeTotalCount = noticeMapper.selectNoticeTotalCount();
        if(noticeTotalCount > 0){
            list = noticeMapper.selectNoticeList();
        }

        return list;
    }

}
