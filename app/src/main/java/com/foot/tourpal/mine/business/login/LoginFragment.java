package com.foot.tourpal.mine.business.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.foot.tourpal.R;
import com.foot.tourpal.base.widget.ClearableEditText;
import com.foot.tourpal.base.widget.VerificationCodeButton;
import com.foot.tourpal.mine.di.component.DaggerLoginComponent;
import com.foot.tourpal.mine.di.module.LoginModule;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import timber.log.Timber;

/**
 * Created by ZhangPu on 2017/6/29.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View, View.OnClickListener{

    private static LoginFragment fragment;
    private final String TAG = this.getClass().getSimpleName();
    private ClearableEditText phoneCET;
    private ClearableEditText codeCET;
    private VerificationCodeButton getVCB;
    private Button loginBT;
    private RxPermissions mRxPermissions;

    public static LoginFragment newInstance() {
        if(fragment == null) {
            fragment = new LoginFragment();
        }
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        this.mRxPermissions = new RxPermissions(getActivity());
//        DaggerUserComponent
//                .builder()
//                .appComponent(appComponent)
//                .userModule(new LoginModule(this))
//                .build()
//                .inject(this);
//        DaggerLoginComponent.builder();
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        phoneCET = (ClearableEditText) getActivity().findViewById(R.id.cet_phone);
        codeCET = (ClearableEditText) getActivity().findViewById(R.id.cet_code);
        getVCB = (VerificationCodeButton) getActivity().findViewById(R.id.vcb_get);
        loginBT = (Button) getActivity().findViewById(R.id.bt_login);
        loginBT.setOnClickListener(this);
        getVCB.setOnClickListener(this);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.vcb_get) {
            Timber.d("click get code");
            String phone = phoneCET.getEditableText().toString();
            mPresenter.requestCode(phone);

        }else if(v.getId() == R.id.bt_login) {

        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
