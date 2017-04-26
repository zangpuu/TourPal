package com.foot.tourpal.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ZhangPu on 2017/4/10.
 */

public class BaseActivity extends AppCompatActivity {

    protected String tag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = getLocalClassName();
    }
}
