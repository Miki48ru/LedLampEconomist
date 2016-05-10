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
import android.widget.Switch;


public class ElectricFragment extends Fragment {
    public ElectricFragment() {
        // Required empty public constructor
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
