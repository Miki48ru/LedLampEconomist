package com.github.miki48ru.ledlampeconomist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import android.support.v7.widget.SwitchCompat;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class ElectricFragment extends Fragment {
    public ElectricFragment() {
        // Required empty public constructor
    }



    private static List<Integer> listSpinnerHours = new ArrayList<>();// список для выбора работы лампочки в часах
    private static List<Integer> listSpinnerRates2Hours = new ArrayList<>();// список для выбора работы лампочки в часах по тарифу 2
    private static List<Integer> listSpinnerPercent = new ArrayList<>(); // список для выбора процента удорожания

    Spinner spinnerHour;
    Spinner spinnerRatesTwoHour;
    Spinner spinnerPercent;

    EditText mPrice;
    EditText mPrice2;

    private int selectedHour;
    private int selectedHourTwoRate;
    private int percent;
    private float summPrice = 0;
    private float summPriceTwoRate = 0;

    private boolean checked;
    final String LOG_TAG = ElectricFragment.class.toString();



    // статически заполняем списки выборов так как никогда не меняются значения
    static {
        for (int i = 1; i <= 24; i++) {
            listSpinnerHours.add(i);
        }
        for (int i = 1; i <= 24; i++) {
            listSpinnerRates2Hours.add(i);
        }
        for (int i = 0; i <=50; i++){
           listSpinnerPercent.add(i);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_electric, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerHour = (Spinner)getActivity().findViewById(R.id.spinner_hours);
        spinnerRatesTwoHour = (Spinner)getActivity().findViewById(R.id.spinner_hours_2);
        spinnerPercent = (Spinner)getActivity().findViewById(R.id.spinner_percent);

        mPrice = (EditText)getActivity().findViewById(R.id.et_price);
        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(LOG_TAG, "mPrice ElectricFrag onTextChanged: " + mPrice.getText().toString());

                Log.d(LOG_TAG, "mPrice ElectricFrag onTextChanged: " + s.toString());
                if(TextUtils.isEmpty(s))
                    s = "0";
                    try {
                        summPrice = Float.parseFloat(s.toString());
                        Data.getInstance().setSummPrice(summPrice);
                        Log.d(LOG_TAG, "summ getInstance: " + Data.getInstance().getSummPrice());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Введены данные неверного формата", Toast.LENGTH_SHORT).show();
                    }
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPrice2 = (EditText)getActivity().findViewById(R.id.et_price_2);
        mPrice2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d(LOG_TAG, "mPrice ElectricFrag onTextChanged: " + s.toString());
                if(TextUtils.isEmpty(s))
                    s = "0";
                try {
                    summPriceTwoRate = Float.parseFloat(s.toString());
                    Data.getInstance().setSummPriceTwoRate(summPriceTwoRate);
                    Log.d(LOG_TAG, "summ getInstance: " + Data.getInstance().getSummPriceTwoRate());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Введены данные неверного формата", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        Button btNext = (Button)getActivity().findViewById(R.id.btNextElectric);
        btNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                FragmentPagerAdapter adapter = (FragmentPagerAdapter)viewPager.getAdapter();
                int pos = 1;
                try {
                    viewPager.setCurrentItem(pos);
                } catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
        });

        final Animation animationFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        final Animation animationFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        final ViewGroup sceneRoot = (ViewGroup) getActivity().findViewById(R.id.main_layout);
        final View tax_layout = getActivity().findViewById(R.id.tax_layout);
        final int visibleHeight = tax_layout.getLayoutParams().height;

        // скрываем группу
        ViewGroup.LayoutParams params = tax_layout.getLayoutParams();
        params.height = 0;
        tax_layout.setLayoutParams(params);

        SwitchCompat swTax = (SwitchCompat) getActivity().findViewById(R.id.sw_double_tax);
        swTax.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SwitchCompat sw = (SwitchCompat) v;
                checked = ((SwitchCompat) v).isChecked();
                Data.getInstance().setChecked(checked);
                if (sw.isChecked()) {
                    tax_layout.startAnimation(animationFadeIn);
                    TransitionManager.beginDelayedTransition(sceneRoot);

                    // и применим сами изменения
                    ViewGroup.LayoutParams params = tax_layout.getLayoutParams();
//                    params.width = 0;
                    params.height = visibleHeight;
                    tax_layout.setLayoutParams(params);
                } else {
                    tax_layout.startAnimation(animationFadeOut);
                    // вызываем метод, говорящий о том, что мы хотим анимировать следующие изменения внутри sceneRoot
                    TransitionManager.beginDelayedTransition(sceneRoot);

                    // и применим сами изменения
                    ViewGroup.LayoutParams params = tax_layout.getLayoutParams();
//                    params.width = 0;
                    params.height = 0;
                    tax_layout.setLayoutParams(params);
                }
            }
        });

        adapterSpinnerHours();
        adapterSpinnerHours2Rate();
        adapterSpinnerPercent();
        setClicklistenerToSpinner();
        setClicklistenerTwoRatesSpinner();
        setClicklistenerPercentSpinner();


    }


    public void adapterSpinnerHours() {
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_item, listSpinnerHours);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(spinnerAdapter);
        spinnerHour.setSelection(1);
    }

    public void adapterSpinnerHours2Rate() {
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_item, listSpinnerRates2Hours);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRatesTwoHour.setAdapter(spinnerAdapter);
        spinnerRatesTwoHour.setSelection(3);
    }

    public void adapterSpinnerPercent() {
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_item, listSpinnerPercent);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPercent.setAdapter(spinnerAdapter);
        spinnerPercent.setSelection(15);
    }

    public void setClicklistenerToSpinner() {
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedHour = (int) spinnerHour.getSelectedItem();
                Data.getInstance().setSelectedHour(selectedHour);
                try {

                    Data.getInstance().setResultTimeYears(selectedHour * 365);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setClicklistenerTwoRatesSpinner() {
        spinnerRatesTwoHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedHourTwoRate = (int) spinnerRatesTwoHour.getSelectedItem();
                Data.getInstance().setSelectedHourTwoRate(selectedHourTwoRate);
                try {

                    Data.getInstance().setResultTimeYearsTwoRate(selectedHourTwoRate * 365);

                } catch (NullPointerException e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setClicklistenerPercentSpinner() {
        spinnerPercent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                percent = (int) spinnerPercent.getSelectedItem();// проценты из раскрывающегося списка по позиции
                Data.getInstance().setPercent(percent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }



}
