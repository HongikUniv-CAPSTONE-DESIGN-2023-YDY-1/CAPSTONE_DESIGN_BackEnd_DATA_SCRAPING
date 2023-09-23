package com.example.demo.entity;

import com.example.demo.dto.Promotion;
import enums.ItemCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    private String subCategory;

    public Item(String name,String imgUrl, ItemCategory category) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.category = category;
    }
    public Item(Promotion promotion){
        String url = promotion.getImgUrl();
        url = url.substring(url.lastIndexOf("/")+1);
        switch (promotion.getBrand()){
            case CU: url = "cu_" + url; break;
            case GS25: url = "gs25_" + url; break;
            case EMART24: url = "emart24_" + url; break;
            case SEVENELEVEN: url = "seven_" + url; break;
        }
        this.name = promotion.getName();
        this.imgUrl = url;
        this.category = promotion.getCategory();
    }
}
