package com.itheima.mapper;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    //查询会员所有数据
    @Select("select * from member")
    List<Member> list();

    //删除会员
    @Delete("delete from member where memberID = #{id}")
    void deleteById(Integer id);

    @Update("update member set memberName = #{memberName}, telephone = #{telephone} where memberID = #{memberID}")
    void updatamember(Member member);

    //添加会员
    @Insert("insert into member (memberName, telephone) value (#{memberName}, #{telephone})")
    void addMember(Member member);
}
