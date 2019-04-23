package com.example.slidershow;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    Toolbar myToolBar;
    ViewPager myViewPager;
    slideShowAdapter myslideShowAdapter;
    CircleIndicator myIndicator;
    Handler myHandler;
    Runnable myRunnable;
    Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //change styles.xml theme to NoActionBar, create a toolbar in visual mode link it to the code below in onCreate override
        myToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myViewPager = (ViewPager) findViewById(R.id.myViewPager); //id of whatever the view pager is named
        myIndicator = (CircleIndicator) findViewById(R.id.circularIndicator);
        myslideShowAdapter = new slideShowAdapter(this);
        myViewPager.setAdapter(myslideShowAdapter);
        myIndicator.setViewPager(myViewPager);
        myHandler = new Handler();
        myRunnable = new Runnable() {
            @Override
            public void run() {
                int i = myViewPager.getCurrentItem();
                //System.out.println(i);
                if (i == myslideShowAdapter.images.length - 1) {

                i=0;
                myViewPager.setCurrentItem(i,true);
                } else {
                    i++;
                    myViewPager.setCurrentItem(i, true);
                }
            }
        };
        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 4000, 4000);

    }
}
