package com.github.miki48ru.ledlampeconomist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LampFragment extends Fragment {
    public LampFragment() {
        // Required empty public constructor
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

        // предположительный способ передачи данных м-ду фрагментами
//        Intent intent;
//        intent.putExtra()
//        getActivity().setIntent(intent);
        Button btNext = (Button)getActivity().findViewById(R.id.bt_next_lamp);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });
    }
}
