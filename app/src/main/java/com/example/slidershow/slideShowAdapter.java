package com.example.slidershow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

//add all this code to a new class (rightclick new-->class// name the class slideShowAdapter)
// make sure to add a viewpager visually and also create a linear layout vertical for the images
// add glide library to build.grandle----- implementation 'com.github.bumptech.glide:glide:4.9.0' ----
// add circular indicator to build.gradle -----  implementation 'me.relex:circleindicator:1.2.2' ----

// in activity_main.xml below the viewpager xml ---  me.relex.circleindicator.CircleIndicator --constrain it to bottom of viewPager height 48dp


public class slideShowAdapter extends PagerAdapter {

    private Context myContext;
    LayoutInflater myInflator;
    public int[] images = {
            R.drawable.building,
            R.drawable.dog,
            R.drawable.coffee,
            R.drawable.volcano

    }; //place images for gallery above here in array,, they should match the vertical order in the linear layout

    public slideShowAdapter(Context myContext) {
        this.myContext = myContext;
    }


    @Override
    public int getCount() {

        return images.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        myInflator = (LayoutInflater) myContext.getSystemService(myContext.LAYOUT_INFLATER_SERVICE);
        final View view = myInflator.inflate(R.layout.slider_layout, container, false); //point to the linear layout xml file name
        ImageView img = (ImageView) view.findViewById(R.id.building); //pick 1st image in gallery
        //  img.setImageResource(images[position]);  ----turn this on if not using glide library---
        Glide.with(myContext).load(images[position]).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"Image" + (position +1),Snackbar.LENGTH_LONG).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return (view == (LinearLayout) o);
    }



}
