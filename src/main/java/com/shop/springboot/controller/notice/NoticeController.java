package com.shop.springboot.controller.notice;

import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/write.do")
    public String openNoticeWrite(@RequestParam(value = "idx", required = false) Long idx, Model model){

        if(idx == null){
            model.addAttribute("notice", new NoticeDto());
        }else{
            NoticeDto dto = noticeService.getNoticeDetail(idx);
            if(dto == null){
                return "redirect:/notice/list.do";
            }
            model.addAttribute("notice", dto);
        }
        return "notice/write";
    }

    @GetMapping("/notice/main.do")
    public String openNoticeList(Model model){
        List<NoticeDto> list = noticeService.getBoardList();

        model.addAttribute("list", list);

        return "notice/main";
    }

    @GetMapping("/notice/update/{idx}")
    public String noticeUpdate(@PathVariable Long idx, Model model){
        NoticeDto noticeDto = noticeService.getNoticeDetail(idx);
        model.addAttribute("noticeDto", noticeDto);

        return "notice/write-update";
    }

    @GetMapping("/notice/read/{idx}")
    public String noticeRead(@PathVariable Long idx, Model model){
        NoticeDto noticeDto = noticeService.getNoticeDetail(idx);
        model.addAttribute("noticeDto", noticeDto);

        return "notice/read";

    }


}
