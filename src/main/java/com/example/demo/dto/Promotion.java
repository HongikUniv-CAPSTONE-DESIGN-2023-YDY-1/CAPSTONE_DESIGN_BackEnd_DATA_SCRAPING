package com.example.demo.dto;

import com.example.demo.entity.Item;
import com.example.demo.entity.PromotionInfo;
import enums.ItemCategory;
import enums.KonbiniBrand;
import enums.PromotionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class Promotion {
    private final String name;
    private ItemCategory category;
    private final PromotionType promotionType;
    private final int pricePerUnit;

    @Setter
    private String imgUrl;

    private final KonbiniBrand brand;

    private final Map<String,Object> subCategory = new HashMap<>();

    public Promotion(Element data, TextNode nameElement,int category,String brand,Element img){
        this(data, nameElement, category, brand);
        if(img == null){
            return;
        }
        String src = img.attr("src");
        if(!src.contains("http")){
            src = "https:"+src;
        }
        this.imgUrl = src;
    }
    private Promotion(Element data, TextNode nameElement,int category,String brand){
        name = data.select("strong").text();
        pricePerUnit = Integer.parseInt(nameElement.toString().replaceAll(" ","")
                .replaceAll(",","")
                .replaceAll("원",""));
        String promotionString = data.select("span.badge.bg-cu.text-white").text();
        promotionType = PromotionType.fromSimpleString(promotionString);
        this.category = ItemCategory.fromInt(category);
        this.brand = Arrays.stream(KonbiniBrand.values())
                .filter(brand1 -> brand1.name().contains(brand.toUpperCase()))
                .findFirst().orElseThrow();
    }

    public PromotionInfo toNewPromotion(){
        return PromotionInfo.builder()
                .brand(this.getBrand())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .price(this.getPricePerUnit())
                .item(new Item(this))
                .promotion(this.getPromotionType())
                .build();
    }

    public Promotion addSubCategory(String name, Object value){
        subCategory.put(name,value);
        return this;
    }

    public void fixCategory(ItemCategory category){
        this.category = category;
        subCategory.clear();
    }
    public Promotion addSubCategory(Map<String,Object> subCategories){
        subCategory.putAll(subCategories);
        return this;
    }
}
