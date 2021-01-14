package com.shop.springboot.controller.notice;

import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/api/v1/notice")
    public Long save(@RequestBody NoticeDto noticeDto){
        boolean temp = noticeService.registerNotice(noticeDto);
        long result = 0;
        if(temp == true){
            result = 1;
        }else{
            result = 0;
        }
        return result;
    }
    @PutMapping("/api/v1/notice/{idx}")
    public Long update(@PathVariable Long idx,
                       @RequestBody NoticeDto noticeDto){

        noticeDto.setIdx((long)idx);
        boolean temp = noticeService.registerNotice(noticeDto);
        long result = 0;
        if(temp == true){
            result = 1;
        }else{
            result = 0;
        }
        return result;
    }

    @DeleteMapping("/api/v1/notice/{idx}")
    public Long delete(@PathVariable Long idx){
        noticeService.deleteNotice(idx);

        return idx;
    }
}
