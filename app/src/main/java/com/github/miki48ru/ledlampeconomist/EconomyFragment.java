package com.github.miki48ru.ledlampeconomist;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class EconomyFragment extends Fragment {

    private static LinearLayout resultsLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_economy, container, false);
        resultsLayout = (LinearLayout)view.findViewById(R.id.llresults);
        return view;



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Data.getInstance().simulateFiveYears();

        Button btNext = (Button)getActivity().findViewById(R.id.btNewStart);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(0);
            }
        });

        Button btMore = (Button)getActivity().findViewById(R.id.btMoreInfo);
        btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.icon_info_title)
                        .setMessage(R.string.icon_info)
                        .setIcon(R.drawable.icon_info)
                        .setCancelable(false)
                        .setNegativeButton(R.string.icon_info_close_button,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


    public void updateData() {
        Data.getInstance().simulateFiveYears();
        if(resultsLayout!=null){
            resultsLayout.removeAllViews();
            for(YearResult yearResult:Data.getInstance().getYearResults()){
                View resultView = LayoutInflater.from(resultsLayout.getContext()).inflate(R.layout.result_layout, null);
                TextView title = (TextView)resultView.findViewById(R.id.tv_results_title);
                TextView lampCost = (TextView)resultView.findViewById(R.id.tv_costs_lamp_1_year);
                TextView ledCost = (TextView)resultView.findViewById(R.id.tv_costs_led_1_year);
                TextView profit = (TextView)resultView.findViewById(R.id.tv_costs_result_1_year);
                title.setText(yearResult.getTitle());
                lampCost.setText(String.valueOf(yearResult.getLampCost()));
                ledCost.setText(String.valueOf(yearResult.getLedLampCost()));
                profit.setText(String.valueOf(yearResult.getProfit()));
                boolean isProfit = yearResult.getProfit()>0;
                int lampStyle = isProfit ? R.style.EconomyValueGray : R.style.EconomyValueOrange;
                int profitStyle = isProfit ? R.style.EconomyValueOrange : R.style.EconomyValueGray;
                lampCost.setTextAppearance(resultsLayout.getContext(),lampStyle);
                ledCost.setTextAppearance(resultsLayout.getContext(),profitStyle);
                profit.setTextAppearance(resultsLayout.getContext(), profitStyle);
                lampCost.setCompoundDrawablesWithIntrinsicBounds(isProfit ? R.drawable.ic_sm_lamp_gray : R.drawable.ic_sm_lamp_orange, 0, isProfit ? R.drawable.ic_rouble_gray : R.drawable.ic_rouble_orange, 0);
                ledCost.setCompoundDrawablesWithIntrinsicBounds(isProfit ? R.drawable.ic_sm_lamp_led_orange : R.drawable.ic_sm_lamp_led_gray, 0, isProfit ? R.drawable.ic_rouble_orange : R.drawable.ic_rouble_gray, 0);
                profit.setCompoundDrawablesWithIntrinsicBounds(isProfit ? R.drawable.ic_sm_lamp_led_orange : R.drawable.ic_sm_lamp_led_gray, 0, isProfit ? R.drawable.ic_rouble_orange : R.drawable.ic_rouble_gray, 0);
                resultsLayout.addView(resultView);
            }
        }
    }
}

