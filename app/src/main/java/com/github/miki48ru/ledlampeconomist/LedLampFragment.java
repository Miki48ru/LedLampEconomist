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


public class LedLampFragment extends Fragment {
    final String LOG_TAG = LedLampFragment.class.toString();
    private static List<Integer> listPowerLed = new ArrayList<>();



    private int selectedPower;
    private float priceLed;

    static TextView tvTimePriceLed;

    private Spinner spinnerPowerLed;



    private EditText mPriceLed;

    static {
        for (int i = 1; i <= 50; i++) {
            listPowerLed.add(i);
        }

    }


    public LedLampFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_led_lamp, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerPowerLed = (Spinner)getActivity().findViewById(R.id.spinner_power_led);
        tvTimePriceLed = (TextView)getActivity().findViewById(R.id.tv_price_led_year);
        mPriceLed = (EditText)getActivity().findViewById(R.id.et_price_led);
        mPriceLed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(TextUtils.isEmpty(s))
                    s = "0";
                try {
                    priceLed = Float.parseFloat(s.toString());
                    Data.getInstance().setPriceLed(priceLed);
                    Log.d(LOG_TAG, "price Led: " + Data.getInstance().getPriceLed());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Введены данные неверного формата", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        Button btNext = (Button)getActivity().findViewById(R.id.bt_next_ledlamp);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(3);
            }
        });
        adapterSpinnerPowerLed();
        setClicklistenerPowerSpinner();

    }

    @Override
    public void onResume() {
        super.onResume();
        setTextResultLed();
    }

    public void adapterSpinnerPowerLed() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(), // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPowerLed);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPowerLed.setAdapter(spinnerAdapter);
        Log.d(LOG_TAG, "adapterSpinnerPower()");
    }

    public void setClicklistenerPowerSpinner() {
        spinnerPowerLed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedPower = (int) spinnerPowerLed.getSelectedItem();// переменная мощности лампы
                Data.getInstance().setPowerLed(selectedPower);

                setTextResultLed();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setTextResultLed() {
        if(tvTimePriceLed!=null)
            tvTimePriceLed.setText(String.valueOf(Data.getInstance().getSummPriceLed()));
    }
}
