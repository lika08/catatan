package com.example.keuangan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class AwalActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        //fill list
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Save Money", "cobbbaaaaa dulu",R.drawable.img1));
        mList.add(new ScreenItem("Happy Your Life", "cobaa dluuuu yaaa",R.drawable.img2));

        //setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);


    }

}
