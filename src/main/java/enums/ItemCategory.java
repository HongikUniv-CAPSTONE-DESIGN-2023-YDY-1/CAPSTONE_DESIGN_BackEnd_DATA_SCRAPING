package enums;

import java.util.Arrays;

public enum ItemCategory {
    BEVERAGE("음료"),
    SNACK("과자류"),
    FOOD("식품"),
    ICE_CREAM("아이스크림"),
    HOUSEHOLD("생활용품");

    private final String label;

    ItemCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static ItemCategory fromValue(String value) {
        return Arrays.stream(values())
                .filter(itemCategory -> itemCategory.label.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enums.ItemCategory: " + value));
    }
    public static ItemCategory fromInt(int value){
        switch (value){
            case 1: return BEVERAGE;
            case 2: return SNACK;
            case 3: return FOOD;
            case 4: return ICE_CREAM;
            case 5: return HOUSEHOLD;
            default: throw new IllegalArgumentException();
        }
    }
}

