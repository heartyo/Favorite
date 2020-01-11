package com.lcxyy.recycleproject;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lcxyy.recycleproject.adapter.RecycleSelectAdapter;
import com.lcxyy.recycleproject.decoration.GridSpacingItemDecoration;
import com.lcxyy.recycleproject.entity.SelectEntity;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        final RecycleSelectAdapter recycleAdapter = new RecycleSelectAdapter(null);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration());
        recyclerView.setAdapter(recycleAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycleAdapter.setSouces(initData());
                recycleAdapter.notifyDataSetChanged();
            }
        }, 1000);

        recycleAdapter.setOnItemClickListener(new RecycleSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClck(View view, int position) {
                Toast.makeText(SelectActivity.this, "select:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<SelectEntity> initData() {
        List<SelectEntity> list = new ArrayList<>();
        list.add(new SelectEntity("交易", "0001"));
        list.add(new SelectEntity("退款", "0002"));
        list.add(new SelectEntity("返现", "0003"));
        list.add(new SelectEntity("营销", "0004"));
        list.add(new SelectEntity("优惠", "0004"));
        return list;
    }
}
