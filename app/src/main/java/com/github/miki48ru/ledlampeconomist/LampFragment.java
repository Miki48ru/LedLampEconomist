package com.github.miki48ru.ledlampeconomist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LampFragment extends Fragment {

    final String LOG_TAG = LampFragment.class.toString();
    private static List<Integer> listPower = new ArrayList<>();
    private static List<Integer> listChangeLamp = new ArrayList<>();



    private int selectedPower;
    private int changeLamp;
    private float priceLamp;

    static TextView tvTimePrice;
    private EditText mPriceLamp;

    Spinner spinnerPowerLamp;
    Spinner spinnerChangeLamp;

    public LampFragment() {
        // Required empty public constructor
    }
    static {
        for (int i = 0; i <= 250; i = i + 10) {
            listPower.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            listChangeLamp.add(i);
        }
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
        mPriceLamp = (EditText)getActivity().findViewById(R.id.et_price_lamp);
        mPriceLamp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s))
                    s = "0";
                try {
                    priceLamp = Float.parseFloat(s.toString());
                    Data.getInstance().setPriceLamp(priceLamp);
                    Log.d(LOG_TAG, "price Lamp: " + Data.getInstance().getPriceLamp());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Введены данные неверного формата", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Button btNext = (Button)getActivity().findViewById(R.id.bt_next_lamp);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });
        spinnerPowerLamp = (Spinner) getActivity().findViewById(R.id.spinner_power_lamp);
        spinnerChangeLamp = (Spinner) getActivity().findViewById(R.id.spinner_change_lamp);
        adapterSpinnerPower();
        setClicklistenerPowerSpinner();
        Log.d(LOG_TAG, "onViewCreated");
        adapterSpinnerChangeLamp();
        setClicklistenerChangeLampSpinner();
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


                setTextResult();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void adapterSpinnerChangeLamp() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(), // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listChangeLamp);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChangeLamp.setAdapter(spinnerAdapter);
    }

    public void setClicklistenerChangeLampSpinner() {
        spinnerChangeLamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                changeLamp = (int) spinnerChangeLamp.getSelectedItem();// переменная мощности лампы
                Data.getInstance().setChangeLamp(changeLamp);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void setTextResult(){

        if(tvTimePrice!=null)
            tvTimePrice.setText(String.valueOf(Data.getInstance().getSummPriceLamp()));
    }


}


