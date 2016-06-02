package com.github.miki48ru.ledlampeconomist;

/**
 * Created by Mike on 02.06.2016.
 */
public class YearResult {
    private String title;
    private float lampCost;
    private float ledLapmCost;
    private float profit;

    public YearResult() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getLampCost() {
        return lampCost;
    }

    public void setLampCost(float lampCost) {
        this.lampCost = lampCost;
    }

    public float getLedLapmCost() {
        return ledLapmCost;
    }

    public void setLedLapmCost(float ledLapmCost) {
        this.ledLapmCost = ledLapmCost;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("YearResult{");
        sb.append("title='").append(title).append('\'');
        sb.append(", lampCost=").append(lampCost);
        sb.append(", ledLapmCost=").append(ledLapmCost);
        sb.append(", profit=").append(profit);
        sb.append('}');
        return sb.toString();
    }
}
