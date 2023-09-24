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
            calcSNACKSubCategory(promotion);
        }
        if(category == FOOD){
            calcFOODSubCategory(promotion);
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

    public void calcSNACKSubCategory(Promotion promotion){
        String jellyRegex = "(?i)(젤리|쁘띠첼|구미|해피세븐|하리보|탱글탱글)";
        String energyBarRegex = "(?i)(에너지바|초코바|코코볼바|콘푸라이트바|콘푸라이트밀크바|오레오밀크스낵|몬테스낵|밀카밀크스낵|조트몬테스낵|로쉐|킨더해피히포)";
        String proteinRegex = "(?i)(단백질|프로틴)";
        String chocoRegex = "(?i)(초콜릿|초코|코코아|킷캣|가나|빈츠|킨더|허쉬|몰트볼)";
        String candyRegex = "(?i)(캔디|사탕|롤리팝|쥬시드랍딥퍼즈|쥬시드랍팝캔디|카라멜|이클립스|마시멜로)";
        String crackerRegex = "(?i)(크래커|리츠|샌드|오레오|비스코프|깜드|빈츠|빠다코코낫|제크|쿠키)";
        String basicSnack = "(?i)(새우깡|치토스|꼬깔콘|나초|도리토스|칩|오징어집|바나나킥|포스틱)";
        String cornSnack = "(?i)(크래커|리츠|샌드|오레오|비스코프|깜드|초코쿠키)";
        String hotSnack = "(?i)(매콤|핫|스파이시|매운|마라)";
        String boxSnack = "(?i)(초코파이|쿠쿠다스|마가렛트|카스타드|몽쉘|빅파이|오예스|후렌치파이|초코하임|화이트하임)";
        String potatoSnack = "(?i)(포테토칩|감자|스윙칩|프링글스)";
        String kidsSnack = "(?i)(에너지바|초코바|코코볼바|콘푸라이트바|콘푸라이트밀크바)";

        boolean isJelly = calcByRegex(jellyRegex, "젤리", promotion);
        boolean isEnergyBar = calcByRegex(energyBarRegex, "에너지바", promotion);
        boolean isProtein = calcByRegex(proteinRegex, "단백질", promotion);
        boolean isChoco = calcByRegex(chocoRegex, "초콜릿", promotion);
        boolean isCandy = calcByRegex(candyRegex, "캔디", promotion);
        boolean isCracker = calcByRegex(crackerRegex, "크래커", promotion);
        boolean isBasicSnack = calcByRegex(basicSnack, "기본 스낵", promotion);
        boolean isCornSnack = calcByRegex(cornSnack, "옥수수 스낵", promotion);
        boolean isHotSnack = calcByRegex(hotSnack, "매운 스낵", promotion);
        boolean isBoxSnack = calcByRegex(boxSnack, "박스 스낵", promotion);
        boolean isPotatoSnack = calcByRegex(potatoSnack, "감자 스낵", promotion);
        boolean isKidsSnack = calcByRegex(kidsSnack, "어린이 스낵", promotion);

        if (!isJelly && !isEnergyBar && !isProtein && !isChoco && !isCandy && !isCracker
                && !isBasicSnack && !isCornSnack && !isHotSnack && !isBoxSnack && !isPotatoSnack && !isKidsSnack) {
            promotion.addSubCategory("기본 스낵", 1);
        }
    }

    public void calcFOODSubCategory(Promotion promotion) {
        // 각각의 정규식 패턴과 boolean 변수를 설정합니다.
        String cupBobRegex = "(?i)(컵밥|컵반|국밥|볶음밥)";
        String iceFoodRegex = "(?!)(만두|교자|피자|닭강정|냉동|치킨|슈마이)";
        String sausageRegex = "(?!)(후랑크|프랑크|핫바|꼬치바|소시지|맥스봉|비엔나|꼬치다|스노우크랩킹" +
                "|오븐구이치킨봉|오븐구이치킨윙|휠터치|크랩|직꾸닭|핫도그|육즙가득|부리또|천하장사)";
        String canFoodRegex = "(?i)(스팸싱글|스팸라이트|스팸클래식|리챔|살코기참치|동원참치|고추참치|야채참치|김치찌개참치)";
        String soupRegex = "(?!)(수프|죽)";
        String healthyFoodRegex = "(?!)(그레인보울|프로틴|닭가슴살|두부|건강|레모나|비타민|치즈|쓹닭|정관장)";
        String riceRegex = "(?!)(햇반|오뚜기밥|흑미밥|잡곡밥|백미밥|현미밥|고시히카리밥|영양밥|약밥|맛있는큰밥|오곡밥)";
        String ramenRegex = "(?!)(라면|너구리|안성탕면|불닭볶음면|짜파게티|마라탕면|무파마|비빔면|배홍동|사리곰탕면|오징어짬뽕" +
                "|진짬뽕|카구리|짜파구리|컵누들|컵쌀국수|큰컵|사발|큰사발|야끼소바불닭|쿠티크투움바파스타|쿠티크매콤물비빔면|짜슐랭" +
                "|치즈게티|우육면|세이면)";
        String tangRegex = "(?!)(찌개|곰탕|설렁탕|삼계탕|추어탕|미역국|무국|장터국|순대국|순댓국|된장국|어묵탕|짜글이)";
        String kimchiRegex = "(?!)(김치|젓갈)";
        String jellyRegex = "(?i)(젤리|쁘띠첼|구미|해피세븐|하리보|탱글탱글)";
        String noodleRegex = "(?i)(촙촙면|스파게티|인천식옛날짜장|장칼국수|하이면|로제불닭떡볶이|납작당면|떡볶이|라뽂이|파스타|사천짜복이컵" +
                "|막국수|김치말이국수)";
        String withDrinkRegex = "(?i)(아몬드|믹스넛|노가리|먹태|육포|어묵탕|포차|족발|머릿|안주|곱창|상상비프|땅콩|무침||소금구이|직화구이|닭발)";
        String hangOverRegex = "(?!)(상쾌환|오징어|케이쿨환|비상대책환|한잔허제간만세|헛깨하니술깨|숙취)";
        String breadRegex = "(?i)(브레디크|크림까눌레|꿀호떡|샌드|와플|요팡|마팡|타이야키)";
        String curryRegex = "(?i)(커리|카레)";
        String cerealRegex = "(?i)(콘푸로스트|시리얼|첵스초코|그래놀라컵|후레이크|크랜베리그래놀라)";

        boolean isCupBob = calcByRegex(cupBobRegex, "컵밥", promotion);
        boolean isIceFood = calcByRegex(iceFoodRegex, "아이스푸드", promotion);
        boolean isSausage = calcByRegex(sausageRegex, "소시지", promotion);
        boolean isCanFood = calcByRegex(canFoodRegex, "통조림", promotion);
        boolean isSoup = calcByRegex(soupRegex, "스프&죽", promotion);
        boolean isHealthyFood = calcByRegex(healthyFoodRegex, "건강식품", promotion);
        boolean isRice = calcByRegex(riceRegex, "즉석밥", promotion);
        boolean isRamen = calcByRegex(ramenRegex, "라면", promotion);
        boolean isTang = calcByRegex(tangRegex, "탕/국/찌개", promotion);
        boolean isKimchi = calcByRegex(kimchiRegex, "김치/젓갈", promotion);
        boolean isJelly = calcByRegex(jellyRegex, "젤리", promotion);
        boolean isNoodle = calcByRegex(noodleRegex, "면류", promotion);
        boolean isWithDrink = calcByRegex(withDrinkRegex, "안주", promotion);
        boolean isHangOver = calcByRegex(hangOverRegex, "숙취해소", promotion);
        boolean isBread = calcByRegex(breadRegex, "빵", promotion);
        boolean isCurry = calcByRegex(curryRegex, "카레", promotion);
        boolean isCereal = calcByRegex(cerealRegex, "시리얼/곡물", promotion);

        // 모든 boolean 변수가 false인 경우 실행합니다.
        if (!isCupBob && !isIceFood && !isSausage && !isCanFood && !isSoup && !isHealthyFood
                && !isRice && !isRamen && !isTang && !isKimchi && !isJelly && !isNoodle
                && !isWithDrink && !isHangOver && !isBread && !isCurry && !isCereal) {
            promotion.addSubCategory("기타",1);
        }
        if (isJelly){
            promotion.fixCategory(SNACK);
            calcSNACKSubCategory(promotion);
        }
    }


}
