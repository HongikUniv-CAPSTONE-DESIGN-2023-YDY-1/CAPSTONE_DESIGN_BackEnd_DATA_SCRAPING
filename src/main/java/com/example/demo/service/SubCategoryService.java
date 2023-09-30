package com.example.demo.service;

import com.example.demo.dto.Promotion;

import java.util.List;

public interface SubCategoryService {
    default List<Promotion> calcSubCategory(List<Promotion> promotions){
        promotions.parallelStream().forEach(this::calcSubCategory);
        return promotions;
    }
    void calcSubCategory(Promotion promotion);
}
