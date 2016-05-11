package com.github.miki48ru.ledlampeconomist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ElectricFragment extends Fragment {
    public ElectricFragment() {
        // Required empty public constructor
    }

    private float priceRateOne;
    private float priceRateTwo;

    private static List<Integer> listSpinnerHours = new ArrayList<>();// список для выбора работы лампочки в часах
    private static List<Integer> listSpinnerRates2Hours = new ArrayList<>();// список для выбора работы лампочки в часах по тарифу 2
    private static List<Integer> listSpinnerPercent = new ArrayList<>(); // список для выбора процента удорожания

    TextView textView;
    Spinner spinnerHour;
    Spinner spinnerRatesTwoHour;
    Spinner spinnerPercent;
    private int resultTimeYears;
    private int resultTimeYearsTwoRate;
    private int selected3;
    private int selected3TwoRate;
    private int percent;
    private float summPrice;
    private float summPriceTwoRate;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_electric, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btNext = (Button)getActivity().findViewById(R.id.btNextElectric);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(1);
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

        Switch swTax = (Switch)getActivity().findViewById(R.id.sw_double_tax);
        swTax.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Switch sw = (Switch)v;
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
    }
}
