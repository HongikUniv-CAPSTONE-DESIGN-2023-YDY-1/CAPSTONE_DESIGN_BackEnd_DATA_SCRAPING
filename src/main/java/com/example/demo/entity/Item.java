package com.example.demo.entity;

import com.example.demo.dto.Promotion;
import enums.ItemCategory;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@TypeDef(name = "json",typeClass = JsonType.class)
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
    @Type(type = "json")
    @Column(columnDefinition = "longtext")
    private Map<String,Object> subCategory = new HashMap<>();
    public Item(String name,String imgUrl, ItemCategory category) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.category = category;
    }
    public Item(Promotion promotion){
        String url = promotion.getImgUrl();
        this.name = promotion.getName();
        this.imgUrl = url;
        this.category = promotion.getCategory();
        this.subCategory = promotion.getSubCategory();
    }

    public Item addSubCategory(String name, Object value){
        subCategory.put(name,value);
        return this;
    }
    public Item addSubCategory(Map<String,Object> subCategories){
        subCategory.putAll(subCategories);
        return this;
    }
}
