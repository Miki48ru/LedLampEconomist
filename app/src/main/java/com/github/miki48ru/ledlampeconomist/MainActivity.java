package com.github.miki48ru.ledlampeconomist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = MainActivity.class.toString();
    ViewPager viewPager;
    int curPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(LOG_TAG, "onPageSelected " + position);
                // получаем ссылку на адаптер фрагментов pager'a
                FragmentPagerAdapter adapter = (FragmentPagerAdapter) viewPager.getAdapter();
                // получаем ссылку на сам фрагмент
                Fragment fragment = adapter.getItem(position);

                try {
                    if (fragment instanceof LampFragment) {
                        ((LampFragment) fragment).setTextResult();
                }

                } catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3", "" };
    private Context context;

    // массив ресурсов-ссылок на иконки для вкладок
    private int[] imageResId = {
            R.drawable.ic_electric_selected,
            R.drawable.ic_lamp_inactive,
            R.drawable.ic_lamp_led_inactive,
            R.drawable.ic_rouble_inactive
    };

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    // создаёт и возвращает фрагмент для указанного № вкладки
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ElectricFragment();
                break;
            case 1:
                fragment = new LampFragment();
                break;
            case 2:
                fragment = new LedLampFragment();
                break;
            case 3:
                fragment = new EconomyFragment();
                break;
            default:
                fragment = new ElectricFragment();
        }
        return fragment;
    }

    // возвращает заголовок вкладки для укзанного №
    @Override
    public CharSequence getPageTitle(int position) {
        // создаём изображение на основе ресурса
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        // вместо обычного текста используем HTML-текст с картинкой
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
