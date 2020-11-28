package com.pakage.googlemapsnearbyplacesapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class IntroSlider extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mdots;

    private TextView[] mdots1;

    private SliderAdapter sliderAdapter;

    private Button Nbutton;

    private Button Sbutton;

    private int CurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);


        mSlideViewPager = (ViewPager) findViewById(R.id.SlideViewPager);
        mdots = (LinearLayout) findViewById(R.id.dots);

        Nbutton = (Button) findViewById(R.id.Next);
        Sbutton = (Button) findViewById(R.id.Skip);


        sliderAdapter = new SliderAdapter(this);

        addDotsIndicator(0);

        mSlideViewPager.setAdapter(sliderAdapter);

        mSlideViewPager.addOnPageChangeListener(viewListener);


        Nbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlideViewPager.getCurrentItem() == mdots1.length -1){
                    Toast.makeText(IntroSlider.this, "Finished!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),UserInfo.class));
                }else {
                    mSlideViewPager.setCurrentItem(CurrentPage + 1);
                }
            }
        });

        Sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserInfo.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }



    public void addDotsIndicator(int position) {

        mdots1 = new TextView[3];
        mdots.removeAllViews();

        for (int i = 0; i < mdots1.length; i++) {

            mdots1[i] = new TextView(this);
            mdots1[i].setText(Html.fromHtml("&#8226"));
            mdots1[i].setTextSize(35);
            mdots1[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mdots.addView(mdots1[i]);
        }

        if (mdots1.length > 0) {
            mdots1[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                addDotsIndicator(i);

                CurrentPage = i;

                if(i == 0){
                    Nbutton.setEnabled(true);
                    Sbutton.setEnabled(false);
                    Sbutton.setVisibility(View.INVISIBLE);

                    Nbutton.setText("Next");
                    Sbutton.setText("");

                }else if(i == mdots1.length - 1){
                    Nbutton.setEnabled(true);
                    Sbutton.setEnabled(true);
                    Sbutton.setVisibility(View.VISIBLE);

                    Nbutton.setText("Finish");
                    Sbutton.setText("Skip");

                }else {
                    Nbutton.setEnabled(true);
                    Sbutton.setEnabled(true);
                    Sbutton.setVisibility(View.VISIBLE);

                    Nbutton.setText("Next");
                    Sbutton.setText("Skip");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };


}
