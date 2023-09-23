package com.example.demo.service;

import com.example.demo.dto.Promotion;
import com.example.demo.entity.Item;
import com.example.demo.entity.PromotionInfo;
import com.example.demo.repository.PromotionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DBPromotionInfoService implements PromotionInfoService {
    private final PromotionInfoRepository repository;
    @Override
    public void saveAll(List<Promotion> promotions) {
        List<PromotionInfo> promotionInfos = promotions.stream().map(promotion ->
                repository.findByBrandAndItem_Name(promotion.getBrand(), promotion.getName()).orElse(promotion.toNewPromotion()))
                .collect(Collectors.toList());
        repository.saveAll(promotionInfos);
    }
}
