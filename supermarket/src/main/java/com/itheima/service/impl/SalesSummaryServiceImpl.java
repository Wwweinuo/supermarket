package com.itheima.service.impl;

import com.itheima.mapper.SalesSummaryMapper;
import com.itheima.pojo.SalesSummary;
import com.itheima.service.SalesSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesSummaryServiceImpl implements SalesSummaryService {
    @Autowired
    private SalesSummaryMapper salesSummaryMapper;

    @Override
    public List<SalesSummary> getSalesD() {
        return salesSummaryMapper.getSalesD();
    }
}
