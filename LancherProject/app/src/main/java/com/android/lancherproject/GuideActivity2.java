package com.android.lancherproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.lancherproject.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

import qiu.niorgai.StatusBarCompat;

public class GuideActivity2 extends AppCompatActivity {

    private static final int[] pics = {R.mipmap.launcher_00, R.mipmap.launcher_01, R.mipmap.launcher_02};
    private List<ImageView> imageViews = null;
    private GuideAdapter mAdapter;
    private int mCurrentItem;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);
        StatusBarCompat.translucentStatusBar(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ViewPager mVp = findViewById(R.id.view_pager);
        imageViews = new ArrayList<>();
        ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(pics[i]);
            imageView.setLayoutParams(mParams);
            imageViews.add(imageView);
        }
        mVp.setAdapter(new GuideAdapter(this, imageViews));
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mVp.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;
            float endX;
            float endY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = event.getX();
                        endY = event.getY();
                        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        Point size = new Point();
                        if (windowManager != null) {
                            windowManager.getDefaultDisplay().getSize(size);
                        }
                        int width = size.x;
                        if(mCurrentItem==(imageViews.size()-1)&&startX-endX>0&&startX-endX>=(width/4)){
                           startActivity(new Intent(GuideActivity2.this,MainActivity.class));
                        }
                        break;
                }
                return false;
            }
        });
    }
}
