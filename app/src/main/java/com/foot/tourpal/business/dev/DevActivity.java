package com.foot.tourpal.business.dev;

import android.content.Intent;
import android.net.Uri;
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
    @BindView(R.id.loginBT)
    Button btLogin;

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
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_location) {
            UiUtils.startActivity(LocationActivity.class);
        } else if (v.getId() == R.id.loginBT) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("App://www.foot.com/LoginActivity"));
            UiUtils.startActivity(intent);
        }
    }
}
