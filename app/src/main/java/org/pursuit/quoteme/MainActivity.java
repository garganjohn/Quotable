package org.pursuit.quoteme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;

import org.pursuit.quoteme.fragment.DisplayFragment;
import org.pursuit.quoteme.fragment.FragmentListener;
import org.pursuit.quoteme.fragment.ViewPagerFragment;
import org.pursuit.quoteme.viewpager.controller.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements FragmentListener {
    public static final String TAG = "Main Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.main_act_viewpager);
        viewPager.setPageMargin(addPadding(10));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getFragmentsForViewPager()));
    }

    public List<Fragment> getFragmentsForViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ViewPagerFragment.getInstance("Motivational Quotes"));
        fragments.add(ViewPagerFragment.getInstance("Demotivational Quotes"));
        fragments.add(ViewPagerFragment.getInstance("Kanye Quotes"));
        return fragments;
    }

    @Override
    public void toDisplayFragment(String str) {
        DisplayFragment displayFragment = DisplayFragment.getInstance(str);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_act_container, displayFragment)
                .addToBackStack("Display Fragment")
                .commit();
    }
    public int addPadding(int dp){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
