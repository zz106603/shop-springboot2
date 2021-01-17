package com.shop.springboot.service.member;

import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.domain.NoticeDto;
import com.shop.springboot.mapper.MemberMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.TextUtils;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public boolean registerMember(MemberDto params){
        int queryResult = 0;

        if(params.getIdx() == null){
            queryResult = memberMapper.insertMember(params);
        }else{
            queryResult = memberMapper.updateMember(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public MemberDto getMemberDetail(Long idx) {
        return memberMapper.selectMemberDetail(idx);
    }

    @Override
    public boolean deleteMember(Long idx) {
        int queryResult = 0;

        MemberDto memberDto = memberMapper.selectMemberDetail(idx);

        if(memberDto != null && "N".equals(memberDto.getDeleteYn())){
            queryResult = memberMapper.deleteMember(idx);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public int idCheck(String memberId) {
        return memberMapper.idCheck(memberId);
    }

    @Override
    public int emailCheck(String memberEmail) {
        return memberMapper.emailCheck(memberEmail);
    }

    @Override
    public MemberDto memberLogin(MemberDto params) {
        return memberMapper.memberLogin(params);
    }
}
