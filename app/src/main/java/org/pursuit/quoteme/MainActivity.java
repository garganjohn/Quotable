package org.pursuit.quoteme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import org.pursuit.quoteme.fragment.DisplayFragment;
import org.pursuit.quoteme.fragment.FragmentListener;
import org.pursuit.quoteme.splashscreen.SplashFrag;
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
        splashScreen();
        ViewPager viewPager = findViewById(R.id.main_act_viewpager);
        viewPager.setPageMargin(addPadding(10));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getFragmentsForViewPager()));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.links_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.github_link:
                //code
                break;
            case R.id.linkedin_link:
                //code
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Fragment> getFragmentsForViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ViewPagerFragment.getInstance("Motivational Quotes"));
        fragments.add(ViewPagerFragment.getInstance("Demotivational Quotes"));
        fragments.add(ViewPagerFragment.getInstance("Kanye Quotes"));
        return fragments;
    }

    public int addPadding(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void splashScreen() {
        SplashFrag splashFrag = new SplashFrag();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_act_container, splashFrag)
                .addToBackStack(null)
                .commit();
    }

    public void closeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }
}
