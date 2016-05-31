package com.github.miki48ru.ledlampeconomist;

/**
 * Created by Mike on 23.05.2016.
 */
public class Data {

    private int resultTimeYears;
    private int resultTimeYearsTwoRate;
    private int selectedHour;
    private int selectedHourTwoRate;
    private int percent;
    private float summPrice = 0;
    private float summPriceTwoRate = 0;
    private boolean checked;

    private static Data instance = new Data();

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }

        return instance;
    }

    public int getResultTimeYears() {
        return resultTimeYears;
    }

    public void setResultTimeYears(int resultTimeYears) {
        this.resultTimeYears = resultTimeYears;
    }

    public int getResultTimeYearsTwoRate() {
        return resultTimeYearsTwoRate;
    }

    public void setResultTimeYearsTwoRate(int resultTimeYearsTwoRate) {
        this.resultTimeYearsTwoRate = resultTimeYearsTwoRate;
    }

    public int getSelectedHour() {
        return selectedHour;
    }

    public void setSelectedHour(int selectedHour) {
        this.selectedHour = selectedHour;
    }

    public int getSelectedHourTwoRate() {
        return selectedHourTwoRate;
    }

    public void setSelectedHourTwoRate(int selectedHourTwoRate) {
        this.selectedHourTwoRate = selectedHourTwoRate;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public float getSummPrice() {
        return summPrice;
    }

    public void setSummPrice(float summPrice) {
        this.summPrice = summPrice;
    }

    public float getSummPriceTwoRate() {
        return summPriceTwoRate;
    }

    public void setSummPriceTwoRate(float summPriceTwoRate) {
        this.summPriceTwoRate = summPriceTwoRate;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
