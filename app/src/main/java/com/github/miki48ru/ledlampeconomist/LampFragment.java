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
        Log.d(LOG_TAG, "onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();

        setTextResult();
    }

    public void adapterSpinnerPower() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(), // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPower);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPowerLamp.setAdapter(spinnerAdapter);
        Log.d(LOG_TAG, "adapterSpinnerPower()");
    }

    public void setClicklistenerPowerSpinner() {
        spinnerPowerLamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Log.d(LOG_TAG, "setClicklistenerPowerSpinner()");
                selectedPower = (int) spinnerPowerLamp.getSelectedItem();// переменная мощности лампы
                Data.getInstance().setSelectedPower(selectedPower);
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

    public void setTextResult(){

        tvTimePrice.setText(String.valueOf(Data.getInstance().getSummPriceLamp()));
    }


}


