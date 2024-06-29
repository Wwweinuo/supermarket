package com.itheima.service.impl;

import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    //显示会员信息
    @Override
    public List<Member> list(){
        List<Member> memberList = memberMapper.list();
        return memberList;
    }
    //删除会员信息
    @Override
    public void delete(Integer id){
        memberMapper.deleteById(id);
    }

    @Override
    public void updatamember(Member member){
        memberMapper.updatamember(member);
    }


    //添加会员
    @Override
    public void addMember(Member member){
        memberMapper.addMember(member);
    }
}
