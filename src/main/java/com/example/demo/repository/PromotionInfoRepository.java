package com.example.demo.repository;

import com.example.demo.entity.PromotionInfo;
import enums.KonbiniBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionInfoRepository extends JpaRepository<PromotionInfo,Long> {
    Optional<PromotionInfo> findByBrandAndItem_Name(KonbiniBrand brand,String name);
}
