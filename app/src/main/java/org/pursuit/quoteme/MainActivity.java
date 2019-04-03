package org.pursuit.quoteme;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashScreen();


        //TODO fix no appCompat since main activity is extending a fragment for the viewpager
        toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.links_menu);

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
                String github = "https://github.com/garganjohn";
                Intent githubIntent = new Intent(Intent.ACTION_VIEW);
                githubIntent.setData(Uri.parse(github));
                startActivity(githubIntent);
                break;
            case R.id.linkedin_link:
                String linkedin = "https://www.linkedin.com/in/john-gargan-97532a59/";
                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW);
                linkedinIntent.setData(Uri.parse(linkedin));
                startActivity(linkedinIntent);
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
