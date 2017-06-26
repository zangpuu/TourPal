package com.foot.tourpal.business.dev;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.foot.tourpal.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;

public class DevActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.bt_location)
    Button btLocation;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_dev;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        btLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_location) {
            UiUtils.startActivity(LocationActivity.class);
        }
    }
}
