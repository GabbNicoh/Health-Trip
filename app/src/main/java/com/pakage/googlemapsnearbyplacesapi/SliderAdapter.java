package com.pakage.googlemapsnearbyplacesapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {


    Context context;
    LayoutInflater inflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Array
    public int[] slide_image = {
            R.drawable.logo1,
            R.drawable.logo2,
            R.drawable.logo3
    };

    public int[] slide_heading = {
            R.drawable.healthtrip_black,
            R.drawable.healthtrip_black,
            R.drawable.healthtrip_black
    };


    public String[] slide_text = {

            "Welcome to Health Trip!\n" + "We're here to help you locate local health facilities.",
            "We provide information and directions to health institutions near you.",
            "With our app, let us help you save your precious time!"
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        ImageView slideHeading = (ImageView) view.findViewById(R.id.slide_heading);
        TextView slideText = (TextView) view.findViewById(R.id.slide_text);


        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setImageResource(slide_heading[position]);
        slideText.setText(slide_text[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
