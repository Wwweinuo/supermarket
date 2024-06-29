package com.itheima.service;

import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    /**
     * 查询会员信息
     * return 存储Member对象的集合
     */
    List<Member> list();

    /**
     * 删除会员信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新会员信息
     * @param member
     */
    void updatamember(Member member);

    /**
     * 添加会员
     * @param member
     */
    void addMember(Member member);

}
