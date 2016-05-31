package com.github.miki48ru.ledlampeconomist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LampFragment extends Fragment {

    final String LOG_TAG = LampFragment.class.toString();
    private static List<Integer> listPower = new ArrayList<>();
    private int resultTimeYearsLampFrg;
    private int resultTimeYearsTwoRateLamp;

    private int percent;

    private float summPriceLampFrg;
    private float summPriceLamp;



    private float summPriceTwoRate;
    private float summPriceTwoRateLamp;


    private int watt = 1000;

    private boolean checked;


    private int selectedPower;

    TextView tvTimePrice;

    Spinner spinnerPowerLamp;

    public LampFragment() {
        // Required empty public constructor
    }
    static {
        for (int i = 0; i <= 250; i = i+10) {
            listPower.add(i);
        }
       /* for (int i = 5; i <= 500; i = i + 5){
            listPrice.add(i);
        }
        for (int i = 0; i <= 10; i++){
            listChangeLamp.add(i);*/
        }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lamp, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTimePrice = (TextView)getActivity().findViewById(R.id.tv_ruble_in_year);


        Button btNext = (Button)getActivity().findViewById(R.id.bt_next_lamp);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });
        spinnerPowerLamp = (Spinner) getActivity().findViewById(R.id.spinner_power_lamp);
        adapterSpinnerPower();
        setClicklistenerPowerSpinner();

    }




    public void adapterSpinnerPower() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(), // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPower);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPowerLamp.setAdapter(spinnerAdapter);
    }

    public void setClicklistenerPowerSpinner() {
        spinnerPowerLamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedPower = (int) spinnerPowerLamp.getSelectedItem();// переменная мощности лампы
                Log.d(LOG_TAG, "selectedPower in setClicklistenerPowerSpinner = " + selectedPower);


//        if (activity != null) {
//            Log.d(LOG_TAG, "data = " + activity.summPrice);
//        } else {
//            Log.d(LOG_TAG, "onAttach getActivity is null");
//        }

                setTextResult();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onSelected() {
        // получаем данные из объекта-синглетона
        summPriceLampFrg = Data.getInstance().getSummPrice();
        Log.d(LOG_TAG, "summPriceLampFrg getInstance in LampFragment = " + summPriceLampFrg);

        summPriceTwoRate = Data.getInstance().getSummPriceTwoRate();
        Log.d(LOG_TAG, "summPriceTwoRate = " + summPriceTwoRate);

        resultTimeYearsLampFrg = Data.getInstance().getResultTimeYears();
        Log.d(LOG_TAG, " resultTimeYearsLampFrg = " +  resultTimeYearsLampFrg);

        resultTimeYearsTwoRateLamp = Data.getInstance().getResultTimeYearsTwoRate();
        percent = Data.getInstance().getPercent();
        checked = Data.getInstance().isChecked();
        Log.d(LOG_TAG, "checked = " + checked);


    }
    public void setTextResult(){
        summPriceLamp = (selectedPower * resultTimeYearsLampFrg / watt * summPriceLampFrg);
        Log.d(LOG_TAG, "result price: " + summPriceLamp + " " + selectedPower + " " + resultTimeYearsLampFrg + " " + watt + " " + summPriceLampFrg);


        // мощность * общее время работы ламп по тарифу 1 / на 1000 ватт * на стоимость тарифа выбранного пользователем (дробное число)
        summPriceTwoRateLamp = (selectedPower * resultTimeYearsTwoRateLamp / watt * summPriceTwoRate);
        Log.d(LOG_TAG, "selectedPower in LampFragment: " + selectedPower);
        Log.d(LOG_TAG, "result price2: " + summPriceTwoRateLamp);

        try {
            // если чекбокс включен, то тариф 2 расчитывается
            if (checked == true) {
                tvTimePrice.setText(String.valueOf(summPriceTwoRateLamp + summPriceLamp));
                //Math.round - округление значения
            } else {
                tvTimePrice.setText(String.valueOf(summPriceLamp));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();

        }
    }

}


