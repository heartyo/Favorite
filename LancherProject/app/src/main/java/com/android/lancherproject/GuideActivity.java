package com.android.lancherproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.lancherproject.launcher.LauncherHolder;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import qiu.niorgai.StatusBarCompat;


public class GuideActivity extends AppCompatActivity implements OnItemClickListener {

    private ArrayList<Integer> pageLists = null;
    private ConvenientBanner<Integer> mBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        StatusBarCompat.translucentStatusBar(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mBanner = findViewById(R.id.convenientBanner);
        if (pageLists == null) {
            pageLists = new ArrayList<>();
        }
        pageLists.add(R.mipmap.launcher_01);
        pageLists.add(R.mipmap.launcher_02);
        pageLists.add(R.mipmap.launcher_03);
        pageLists.add(R.mipmap.launcher_04);
        pageLists.add(R.mipmap.launcher_05);
        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LauncherHolder(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_localimage;
            }
        }, pageLists)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false)
        ;

    }

    @Override
    public void onItemClick(int position) {

    }
}
