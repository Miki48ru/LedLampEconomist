package com.github.miki48ru.ledlampeconomist;

/**
 * Created by Mike on 02.06.2016.
 */
public class YearResult {
    private String title;
    private float lampCost;
    private float ledLampCost;
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

    public float getLedLampCost() {
        return ledLampCost;
    }

    public void setLedLampCost(float ledLapmCost) {
        this.ledLampCost = ledLapmCost;
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
        sb.append(", ledLapmCost=").append(ledLampCost);
        sb.append(", profit=").append(profit);
        sb.append('}');
        return sb.toString();
    }
}
