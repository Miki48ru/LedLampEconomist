package com.github.miki48ru.ledlampeconomist;

import android.util.Log;

import java.util.ArrayList;

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
    private float priceLamp;
    private int changeLamp;
    private int selectedPower;
    private int watt = 1000;
    private float priceLed;
    private int powerLed;

    public Data() {
        yearResults = new ArrayList<YearResult>();
    }

    private ArrayList<YearResult> yearResults;

    public float getPriceLed() {
        return priceLed;
    }

    public void setPriceLed(float priceLed) {
        this.priceLed = priceLed;
    }

    public int getPowerLed() {
        return powerLed;
    }

    public void setPowerLed(int powerLed) {
        this.powerLed = powerLed;
    }

    public float getPriceLamp() {
        return priceLamp;
    }

    public void setPriceLamp(float priceLamp) {
        this.priceLamp = priceLamp;
    }

    public int getChangeLamp() {
        return changeLamp;
    }

    public void setChangeLamp(int changeLamp) {
        this.changeLamp = changeLamp;
    }


    public int getSelectedPower() {
        return selectedPower;
    }

    public void setSelectedPower(int selectedPower) {
        this.selectedPower = selectedPower;
    }

    public int getWatt() {
        return watt;
    }

    public void setWatt(int watt) {
        this.watt = watt;
    }

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
        return percent / 100 + 1;
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


    public float getSummPriceLamp(){
        if(checked){
            float summPriceLamp = (selectedPower * resultTimeYears / watt * summPrice);
            float summPriceTwoRateLamp = (selectedPower * resultTimeYearsTwoRate / watt * summPriceTwoRate);
            return summPriceTwoRateLamp + summPriceLamp;
        }else {
            float summPriceLamp = (selectedPower * resultTimeYears / watt * summPrice);
            return summPriceLamp;
        }
    }
    public float getSummPriceLed(){
        if(checked){
            float summPriceLed = (powerLed * resultTimeYears / watt * summPrice);
            float summPriceTwoRateLed = (powerLed * resultTimeYearsTwoRate / watt * summPriceTwoRate);
            return summPriceTwoRateLed + summPriceLed;
        }else {
            float summPriceLed = (powerLed * resultTimeYears / watt * summPrice);
            return summPriceLed;
        }
    }

    public float getResultPriceLampAllRates(){
        float resultPriceLampAllRates = Math.round(((float) selectedPower / watt * resultTimeYears * summPrice) +
                ((float) selectedPower / watt * resultTimeYearsTwoRate * summPriceTwoRate) +
                (changeLamp * priceLamp) + priceLamp); //Math.round - округление значения
        return resultPriceLampAllRates;
    }
    public float getResultPriceLampOneRates(){
        float resultPriceLampOneRate = Math.round(((float) selectedPower / watt * resultTimeYears * summPrice) +
                (changeLamp * priceLamp) + priceLamp);
        return resultPriceLampOneRate;
    }
    public float getResultPriceLedAllRates(){
        float resultPriceLedAllRates = Math.round(((float) powerLed / watt * resultTimeYears * summPrice) +
                ((float) powerLed / watt * resultTimeYearsTwoRate * summPriceTwoRate) + priceLamp); //Math.round - округление значения
        return resultPriceLedAllRates;
    }

    public float getResultPriceLedOneRates(){
        float resultPriceLedOneRate = Math.round(((float) powerLed / watt * resultTimeYears * summPrice) + priceLamp);
        return resultPriceLedOneRate;
    }

   public void simulateFiveYears(){
//тут расчет для первого года,
       /* YearResult firstYearResult = new YearResult();
        firstYearResult.setTitle("Результат за первый год");
        firstYearResult.setLampCost(значение);
        firstYearResult.setLedLampCost(значение);
        firstYearResult.setProfit(значение);*/
//а после
        // yearResults.add(firstYearResult);
//и так рассчитать для всех 5 лет
    }


}
