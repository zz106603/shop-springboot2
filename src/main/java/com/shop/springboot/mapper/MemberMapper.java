package com.shop.springboot.mapper;

import com.shop.springboot.domain.MemberDto;
import com.shop.springboot.domain.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public int insertMember(MemberDto params);
    public MemberDto selectMemberDetail(Long idx);
    public int updateMember(MemberDto params);
    public int deleteMember(Long idx);
    public int idCheck(String memberId);
}
