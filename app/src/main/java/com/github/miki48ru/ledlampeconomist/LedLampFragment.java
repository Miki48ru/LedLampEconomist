package com.github.miki48ru.ledlampeconomist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class LedLampFragment extends Fragment {
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

        Button btNext = (Button)getActivity().findViewById(R.id.bt_next_ledlamp);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(3);
            }
        });
    }
}
