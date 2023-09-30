package com.example.demo.entity;

import com.example.demo.dto.Promotion;
import enums.KonbiniBrand;
import enums.PromotionType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "promotion_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PromotionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand_id", nullable = false)
    private KonbiniBrand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "promotion_id", nullable = false)
    private PromotionType promotion;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "price", nullable = false)
    private int price;

    public void update(Promotion promotion){
        brand = promotion.getBrand();
        this.promotion = promotion.getPromotionType();
        startDate = LocalDate.now();
        endDate = LocalDate.now();
        price = promotion.getPricePerUnit();
        item.update(promotion);
    }
}