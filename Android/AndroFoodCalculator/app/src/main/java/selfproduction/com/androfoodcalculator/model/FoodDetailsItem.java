package selfproduction.com.androfoodcalculator.model;

/**
 * Created by anna on 28.12.16.
 */

public class FoodDetailsItem {
    private FoodGroupItem foodGroupItem;
    private double price;
    private double count;

    public FoodGroupItem getFoodGroupItem() {
        return foodGroupItem;
    }

    public void setFoodGroupItem(FoodGroupItem foodGroupItem) {
        this.foodGroupItem = foodGroupItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
