package com.android.lancherproject.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.android.lancherproject.R;
import com.bigkoo.convenientbanner.holder.Holder;

public class LauncherHolder extends Holder<Integer> {
    private AppCompatImageView mAppCompatImageView;


    public LauncherHolder(View itemView) {
        super(itemView);

    }

    @Override
    protected void initView(View itemView) {
        mAppCompatImageView = itemView.findViewById(R.id.ivPost);

    }

    @Override
    public void updateUI(Integer data) {
        mAppCompatImageView.setImageResource(data);
    }


}
