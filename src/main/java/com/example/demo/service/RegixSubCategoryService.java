package com.example.demo.service;

import com.example.demo.dto.Promotion;
import enums.ItemCategory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static enums.ItemCategory.*;

@Service
public class RegixSubCategoryService implements SubCategoryService {
    @Override
    public void calcSubCategory(Promotion promotion) {
        ItemCategory category = promotion.getCategory();
        if(category == BEVERAGE){
            calcBEVERAGESubCategory(promotion);
        }
        if(category == SNACK){
        }
        if(category == FOOD){
        }
        if(category == ICE_CREAM){
        }
        if(category == HOUSEHOLD){
        }
    }
    private void calcBEVERAGESubCategory(Promotion promotion){
        String cafeRegex = "(?i)(커피|에스프레소|카푸치노|마끼아또|모카|콜드\\s브루|아이스\\s커피|디카페인|아라비카|로부스타|바닐라|헤이즐넛|캐러멜|스타벅스|카페|콜드브루|다방)";
        boolean isCafe = calcByRegex(cafeRegex,"커피", promotion);

        String milkRegex = "(?i)(우유|라떼|크림|밀크|우유셰이크|요구루트|요거트|비요뜨|요거톡|불가리스|요플|플레인|액티비아)";
        boolean isMilky = calcByRegex(milkRegex,"우유와 요거트", promotion);

        String zeroRegex = "(?i)(제로)";
        boolean isZero = calcByRegex(zeroRegex,"제로", promotion);

        String fruitRegex = "(?i)(망고|딸기|바나나|사과|오렌지|포도|수박|파인애플|체리|레몬|라즈베리|블루베리|키위|복숭아|샤인머스|베리|자몽|과일|과즙|청귤|알로에|유자|과일야채샐러드|워터멜론|피치)";
        boolean isFruit = calcByRegex(fruitRegex,"과일", promotion);

        String drinkRegex = "(?i)(컨티션|헛개|비타민|천지개벽|상쾌환)";
        boolean isDrink = calcByRegex(drinkRegex, "숙취", promotion);

        String sodaRegex = "(?i)(콜라|사이다|환타|웰치스|탄산|톡톡|맥콜|소다|스파클링|핫식스|밀키스|나랑드)";
        boolean isSoda = calcByRegex(sodaRegex,"탄산",promotion);

        String vitaminRegex = "(?i)(비타)";
        boolean isVitamin = calcByRegex(vitaminRegex, "비타민", promotion);

        String healthyDrinkRegex = "(?i)(보리|옥수수수염|우엉|홍삼|누룽지|유자|결명자|과일야채샐러드|쌍화|솔의눈|녹차|말차|17차|십칠차)";
        boolean isHealthyDrink = calcByRegex(healthyDrinkRegex, "건강음료", promotion);

        String energyDrinkRegex = "(?i)(핫식스|몬스터|생생톤|박카스)";
        boolean isEnergyDrink = calcByRegex(energyDrinkRegex, "에너지드링크", promotion);
        if (!isCafe && !isMilky && !isZero && !isFruit && !isDrink && !isSoda && !isVitamin && !isHealthyDrink && !isEnergyDrink) {
            promotion.addSubCategory("기타", 1);
        }
    }
    private boolean calcByRegex(String regex, String keyword, Promotion promotion){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(promotion.getName());
        if(matcher.find()){
            promotion.addSubCategory(keyword, 1);
            return true;
        }
        return false;
    }
}
