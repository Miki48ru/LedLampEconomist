package com.github.miki48ru.ledlampeconomist;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Mike on 23.05.2016.
 */
public class Data {

    final String LOG_TAG = "my logs";

    private float resultTimeYears;
    private float resultTimeYearsTwoRate;
    private int selectedHour;
    private int selectedHourTwoRate;
    private int percent;
    private float summPrice;
    private float summPriceTwoRate;
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

    public ArrayList<YearResult> getYearResults() {
        return yearResults;
    }

    public void setYearResults(ArrayList<YearResult> yearResults) {
        this.yearResults = yearResults;
    }

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

    public float getResultTimeYears() {
        return resultTimeYears;
    }

    public void setResultTimeYears(int resultTimeYears) {
        this.resultTimeYears = resultTimeYears;
    }

    public float getResultTimeYearsTwoRate() {
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
            float summPriceTwoRateLamp =(selectedPower * resultTimeYearsTwoRate / watt * summPriceTwoRate);
            return new BigDecimal(summPriceLamp + summPriceTwoRateLamp).setScale(2, RoundingMode.DOWN).floatValue();  //summPriceTwoRateLamp + summPriceLamp;
        }else {
            float summPriceLamp = (selectedPower * resultTimeYears / watt * summPrice);
            return new BigDecimal(summPriceLamp).setScale(2, RoundingMode.DOWN).floatValue();
        }
    }
    public float getSummPriceLed(){
        if(checked){
            float summPriceLed = (powerLed * resultTimeYears / watt * summPrice);
            float summPriceTwoRateLed = (powerLed * resultTimeYearsTwoRate / watt * summPriceTwoRate);
            return new BigDecimal(summPriceLed + summPriceTwoRateLed).setScale(2, RoundingMode.DOWN).floatValue();
        }else {
            float summPriceLed = (powerLed * resultTimeYears / watt * summPrice);
            return new BigDecimal(summPriceLed).setScale(2, RoundingMode.DOWN).floatValue();
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

    /**
     * Расчет стоимости работы лампы по заданым парметрам.
     * При вычислении результат для многих годов нужно учитывать несколько правил:
     * 1. Для первого года указывать параметр yearNumber = 0, для последующих 1,2,.
     * 2. Для второго и последующих лет нужно lampPrice указывать 0, но replacePrice везде указвать равную lampPrice на первом году.
     * 3. Если нет второго тарифа, то передать значение tarif2 = 0. Тогда часть secondTarif обнулиться.
     * 4. Количество дней можно указать любое, по умолчанию использовать 365.
     * 5. Процент увеличения тарифа везде указывать. Отсутствие подоражания в первый год учитывается параметром yearNumber.
     * 6. Это рассчитывается результат на 1 год.
     *
     * @param lampPower мощность лампы в ваттах
     * @param tarif1 первый тариф в киловаттах за час
     * @param workTime1 время работы в часах лампы по первому тарифу
     * @param tarif2 второй тариф в киловаттах за час
     * @param workTime2 время работы в часах лампы по второму тарифу
     * @param dayCount количество дней, для которых рассчитываем
     * @param lampPrice стоимость лампы при первоначальной покупке. Для второго и последующих годов указывать 0.
     * @param replacePrice стоимость лампы на замену. По умолчанию указывать как lampPrice, но для всех лет. Это важно!
     * @param replaceCount количество замен лампы в году
     * @param percent процент подоражания тарифа
     * @param yearNumber номер года. Начальный указывать 0, а после 1,2,3. Это важно!
     *
     * @return стоимость работы лампы в течении dayCount. Результат округлен до второго знака после запятой.
     */
    private double getLampResult(int lampPower, float tarif1, int workTime1,
                                 float tarif2, int workTime2, int dayCount,
                                 float lampPrice, float replacePrice, int replaceCount,
                                 int percent, int yearNumber){
        double yearPart = (yearNumber*((double)percent/100)+1)*dayCount;
        double lampPowerKilowatt = ((double)lampPower/1000);
        double firstTarif = lampPowerKilowatt*tarif1*workTime1*yearPart;
        double secondTarif = lampPowerKilowatt*tarif2*workTime2*yearPart;
        double lampCost = lampPrice + replacePrice * replaceCount;
        double result = firstTarif + secondTarif + lampCost;
        //округление до вторго знака
        return new BigDecimal(result).setScale(2, RoundingMode.DOWN).doubleValue();
    }

    public void simulateFiveYears(){
        //<editor-fold desc="Логи входных данных">
        Log.e("Данные:обычная лампа:","Мощность лампы:"+selectedPower+"\nТариф 1:"+summPrice+"\nВремя работы Т1:"+selectedHour
                +"\nТариф 2:"+summPriceTwoRate+"\nВремя работы Т2:"+selectedHourTwoRate+"\nКол-во дней:"+365
                +"\nСтоимость лампы:"+priceLamp+"\nСтоимость замены:"+priceLamp+"\nКол-во замен:"+changeLamp
                +"\n% увеличения:"+percent+"\nГод:первый");
        Log.e("Данные:led лампа:","Мощность лампы:"+powerLed+"\nТариф 1:"+summPrice+"\nВремя работы Т1:"+selectedHour
                +"\nТариф 2:"+summPriceTwoRate+"\nВремя работы Т2:"+selectedHourTwoRate+"\nКол-во дней:"+365
                +"\nСтоимость лампы:"+priceLed+"\nСтоимость замены:"+0+"\nКол-во замен:"+0
                +"\n% увеличения:"+percent+"\nГод:первый");
        //</editor-fold>

        yearResults.clear();

        //<editor-fold desc="Расчет для первого года">
        float lampFirstY = (float) getLampResult(selectedPower, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, priceLamp, priceLamp, changeLamp, percent, 0);
        float ledlampFirstY = (float) getLampResult(powerLed, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, priceLed, 0, 0, percent, 0);

        float delta = lampFirstY - ledlampFirstY;

        YearResult firstYearResult = new YearResult();
        firstYearResult.setTitle("Результат за первый год");
        firstYearResult.setLampCost(lampFirstY);
        firstYearResult.setLedLampCost(ledlampFirstY);
        firstYearResult.setProfit(delta);
        Log.e("Сравнение:первый год",firstYearResult.toString());
        yearResults.add(firstYearResult);
        //</editor-fold>

        //<editor-fold desc="Расчет для вторго года">
        //для второго года
        float lampSecondY = (float) getLampResult(selectedPower, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, priceLamp, changeLamp, percent, 1);
        float ledlampSecondY = (float) getLampResult(powerLed, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, 0, 0, percent, 1);

        lampSecondY = lampFirstY+lampSecondY;
        ledlampSecondY = ledlampFirstY+ledlampSecondY;

        float deltaSecond = lampSecondY - ledlampSecondY;

        YearResult secondYearResult = new YearResult();
        secondYearResult.setTitle("Результат за второй год");
        secondYearResult.setLampCost(lampSecondY);
        secondYearResult.setLedLampCost(ledlampSecondY);
        secondYearResult.setProfit(deltaSecond);
        yearResults.add(secondYearResult);
        Log.e("Сравнение:второй год",secondYearResult.toString());
        //</editor-fold>

        //<editor-fold desc="Расчет для третьего года">
        //для второго года
        float lampThirdY = (float) getLampResult(selectedPower, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, priceLamp, changeLamp, percent, 2);
        float ledlampThirdY = (float) getLampResult(powerLed, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, 0, 0, percent, 2);

        lampThirdY = lampSecondY + lampThirdY;
        ledlampThirdY = ledlampSecondY + ledlampThirdY;

        float deltaThird = lampThirdY - ledlampThirdY;

        YearResult thirdYearResult = new YearResult();
        thirdYearResult.setTitle("Результат за третий год");
        thirdYearResult.setLampCost(lampThirdY);
        thirdYearResult.setLedLampCost(ledlampThirdY);
        thirdYearResult.setProfit(deltaThird);
        yearResults.add(thirdYearResult);
        Log.e("Сравнение:третий год",thirdYearResult.toString());
        //</editor-fold>

        //<editor-fold desc="Расчет для четвертого года">
        //для второго года
        float lampFourY = (float) getLampResult(selectedPower, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, priceLamp, changeLamp, percent, 3);
        float ledlampFourY = (float) getLampResult(powerLed, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, 0, 0, percent, 3);

        lampFourY = lampThirdY + lampFourY;
        ledlampFourY = ledlampThirdY + ledlampFourY;

        float deltaFour = lampFourY - ledlampFourY;

        YearResult fourYearResult = new YearResult();
        fourYearResult.setTitle("Результат за четвертый год");
        fourYearResult.setLampCost(lampFourY);
        fourYearResult.setLedLampCost(ledlampFourY);
        fourYearResult.setProfit(deltaFour);
        yearResults.add(fourYearResult);
        Log.e("Сравнение:Четвертый год",fourYearResult.toString());
        //</editor-fold>

        //<editor-fold desc="Расчет для пятого года">
        //для второго года
        float lampFifthY = (float) getLampResult(selectedPower, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, priceLamp, changeLamp, percent, 4);
        float ledlampFifthY = (float) getLampResult(powerLed, summPrice, selectedHour, summPriceTwoRate,
                selectedHourTwoRate, 365, 0, 0, 0, percent, 4);

        lampFifthY = lampFourY + lampFifthY;
        ledlampFifthY = ledlampFourY + ledlampFifthY;

        float deltaFifth = lampFifthY - ledlampFifthY;

        YearResult fifthYearResult = new YearResult();
        fifthYearResult.setTitle("Результат за пятый год");
        fifthYearResult.setLampCost(lampFifthY);
        fifthYearResult.setLedLampCost(ledlampFifthY);
        fifthYearResult.setProfit(deltaFifth);
        yearResults.add(fifthYearResult);
        Log.e("Сравнение:Пятый год",fifthYearResult.toString());
        //</editor-fold>

        //Log.e("РЕЗУЛЬТАТ: ", yearResults.toString());

    }
}
