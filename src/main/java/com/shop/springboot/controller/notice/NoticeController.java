package com.shop.springboot.controller.notice;

import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

        if(idx == null){
            //올바르지 않은 접근
            return "redirect:/notice/main.do";
        }
        NoticeDto noticeDto = noticeService.getNoticeDetail(idx);
        if(noticeDto == null || "Y".equals(noticeDto.getDeleteYn())){
            //없는 게시글이거나 이미 삭제된 게시글
            return "redirect:/notice/main.do";
        }
        model.addAttribute("noticeDto", noticeDto);

        return "notice/write-update";
    }

    @GetMapping("/notice/read/{idx}")
    public String noticeRead(@PathVariable Long idx, Model model){

        if(idx == null){
            //올바르지않은 접근
            return "redirect:/notice/main.do";
        }
        NoticeDto noticeDto = noticeService.getNoticeDetail(idx);
        if(noticeDto == null || "Y".equals(noticeDto.getDeleteYn())){
            //없는 게시글이거나 이미 삭제된 게시글
            return "redirect:/notice/main.do";
        }
        model.addAttribute("noticeDto", noticeDto);

        return "notice/read";

    }


}
