package com.shop.springboot.service.member;

import com.shop.springboot.domain.MemberDto;

public interface MemberService {

    //회원가입
    public boolean registerMember(MemberDto params);
    public MemberDto getMemberDetail(Long idx);
    public boolean deleteMember(Long idx);
    public int idCheck(String memberId);

}
