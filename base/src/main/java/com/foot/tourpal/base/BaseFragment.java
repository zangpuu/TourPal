package com.foot.tourpal.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by ZhangPu on 2017/4/10.
 */

public class BaseFragment extends Fragment {

    protected String tag;

    public static BaseFragment newInstance() {

        Bundle args = new Bundle();

        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = this.getClass().getName();
    }
}
