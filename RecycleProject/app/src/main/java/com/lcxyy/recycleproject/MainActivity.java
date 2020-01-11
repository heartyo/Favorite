package com.lcxyy.recycleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcxyy.recycleproject.adapter.RecycleAdapter;
import com.lcxyy.recycleproject.decoration.GridSpacingItemDecoration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecycleAdapter recycleAdapter = new RecycleAdapter();
        recyclerView.addItemDecoration(new GridSpacingItemDecoration());
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClck(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, SelectActivity.class));
                }
            }
        });

    }


}
