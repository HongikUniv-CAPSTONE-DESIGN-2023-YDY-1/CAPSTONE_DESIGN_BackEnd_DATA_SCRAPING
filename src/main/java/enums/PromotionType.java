package enums;

public enum PromotionType {
    ONE_PLUS_ONE,TWO_PLUS_ONE;

    public static PromotionType fromSimpleString(String s){
        if(s.equals("2+1"))return TWO_PLUS_ONE;
        else return ONE_PLUS_ONE;
    }
}
