package org.pursuit.quoteme;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {
    public static final String TAG = "Main Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ViewPager viewPager = findViewById(R.id.main_act_viewpager);
         //viewPager.setAdapter(getSupportFragmentManager(), );
    }
}
