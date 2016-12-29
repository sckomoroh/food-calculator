package com.selfproduction;

/**
 * Created by sckomoroh on 26.12.16.
 */
public class MainTableItem {
    private String title;
    private double price;
    private double count;
    private boolean include;

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

    public double getActualPrice() {
        return price * count;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInclude(boolean include)
    {
        this.include = include;
    }

    public boolean isInclude()
    {
        return include;
    }
}
