package com.example.keuangan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AwalActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndacator;
    Button btnNext;
    int positon = 0;
    Button btnGetStarted;
    Animation btnAnim;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //membuat layar full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_awal);

        //HIDEN action bar
        getSupportActionBar().hide();

//        ini view
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndacator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.transition.button_animation);

        //fill list
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Save Money", "cobbbaaaaa dulu",R.drawable.img1));
        mList.add(new ScreenItem("Happy Your Life", "cobaa dluuuu yaaa",R.drawable.img2));

        //setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

//        setup tablayout dgn viewpage
        tabIndacator.setupWithViewPager(screenPager);

        //button next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positon = screenPager.getCurrentItem();
                if (positon < mList.size()){

                    positon++;
                    screenPager.setCurrentItem(positon);
                }

                if(positon == mList.size()-1) {

                    loaddLastScreen();
                }
            }
        });

        tabIndacator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){

                    loaddLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//
//        btnGetStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainActivity = new Intent (getApplicationContext(), MainActivity.class);
//                startActivity(mainActivity);
//            }
//        });


    }

    private void loaddLastScreen(){

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndacator.setVisibility(View.INVISIBLE);

        btnGetStarted.setAnimation(btnAnim);

    }
}
