package com.itheima.controller;

import com.itheima.pojo.Member;
import com.itheima.pojo.Result;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public Result list(){
        List<Member> memberList = memberService.list();
        return Result.success(memberList);
    }

    @DeleteMapping("/memberdelete")
    public Result delete(@RequestParam Integer memberID){
        memberService.delete(memberID);
        return Result.success();
    }

    @PostMapping("/member/edit")
    public Result updataMember(@RequestBody Member member){
        memberService.updatamember(member);
        return Result.success();
    }

    @PostMapping("/member/add")
    public Result addMember(@RequestBody Member member){
        memberService.addMember(member);
        return Result.success();
    }


}
