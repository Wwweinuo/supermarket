package com.itheima.service;

import com.itheima.pojo.SalesSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesSummaryService {
    List<SalesSummary> getSalesD();
}
