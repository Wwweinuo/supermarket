package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.SalesSummary;
import com.itheima.service.SalesSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalesSummaryController {
    @Autowired
    private SalesSummaryService salesSummaryService;

    @GetMapping("/salesSummary/get")
    public Result getSales(){
        List<SalesSummary> salesSummaries = salesSummaryService.getSalesD();
        return Result.success(salesSummaries);
    }

}
