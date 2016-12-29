package selfproduction.com.androfoodcalculator.model;

/**
 * Created by anna on 28.12.16.
 */

public class FoodGroupItem {
    private FoodDetailsItem foodDetailsItem;
    private String name;
    private boolean include;

    public FoodGroupItem(FoodDetailsItem foodDetailsItem)
    {
        this.foodDetailsItem = foodDetailsItem;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

    public FoodDetailsItem getFoodDetailsItem() {
        return foodDetailsItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getActualPrice() {
        return foodDetailsItem.getPrice() * foodDetailsItem.getCount();
    }
}
