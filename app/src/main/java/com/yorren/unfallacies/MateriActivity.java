package com.yorren.unfallacies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yorren.unfallacies.adapter.SliderAdapter;
import com.yorren.unfallacies.fragment.HomeFragment;

public class MateriActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private SliderAdapter sliderAdapter;
    FloatingActionButton fabBack;
    TextView tvSwipe;
    Button btnLatihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        mSlideViewPager = (ViewPager) findViewById(R.id.viewPager2);
        tvSwipe = findViewById(R.id.tvSwipe);
        btnLatihan = findViewById(R.id.btnLatihan);
        fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(v -> {
            startActivity(new Intent(MateriActivity.this, DashboardActivity.class));
            finish();
        });

        btnLatihan.setOnClickListener(v -> {
            Intent intent = new Intent(MateriActivity.this, ChatActivity.class);
            intent.putExtra("starter", "Hai pada ngobrolin apa ini gimana hasil rapotnya");
            this.startActivity(intent);
        });

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 2){
                btnLatihan.setVisibility(View.VISIBLE);
                tvSwipe.setVisibility(View.GONE);
            }else{
                btnLatihan.setVisibility(View.GONE);
                tvSwipe.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}