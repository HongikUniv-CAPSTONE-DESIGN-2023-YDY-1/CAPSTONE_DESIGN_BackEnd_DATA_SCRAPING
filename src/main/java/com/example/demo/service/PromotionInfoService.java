package com.example.demo.service;

import com.example.demo.dto.Promotion;

import java.util.List;

public interface PromotionInfoService {
    void saveAll(List<Promotion> promotions);
}
