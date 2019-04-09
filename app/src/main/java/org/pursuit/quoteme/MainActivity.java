package org.pursuit.quoteme;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.links_menu);

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
                String github = getString(R.string.github_link);
                Intent githubIntent = new Intent(Intent.ACTION_VIEW);
                githubIntent.setData(Uri.parse(github));
                startActivity(githubIntent);
                break;
            case R.id.linkedin_link:
                String linkedin = getString(R.string.linkedin_link);
                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW);
                linkedinIntent.setData(Uri.parse(linkedin));
                startActivity(linkedinIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Fragment> getFragmentsForViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ViewPagerFragment.getInstance(getString(R.string.motivational_quotes)));
        fragments.add(ViewPagerFragment.getInstance(getString(R.string.demotivational_quotes)));
        fragments.add(ViewPagerFragment.getInstance(getString(R.string.kanye_quotes)));
        return fragments;
    }

    private int addPadding(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private void splashScreen() {
        getSupportActionBar().hide();
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
        getSupportActionBar().show();
    }
}
