package com.foot.tourpal.business.home.presenter;

import android.Manifest;
import android.app.Application;

import com.foot.tourpal.business.home.contract.HomeContarct;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.PermissionUtil;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * Created by ZhangPu on 2017/7/11.
 */

public class HomePresenter extends BasePresenter <HomeContarct.Model, HomeContarct.View> {

    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;

    @Inject
    public HomePresenter(HomeContarct.Model model, HomeContarct.View rootView, RxErrorHandler handler,
                         AppManager appManager, Application application){
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
        this.mApplication = application;
    }

    public void requestPermission(){
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.requestPermission(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
            }

            @Override
            public void onRequestPermissionFailure() {
                mRootView.showMessage("Request permissons failure");
            }
        }, mRootView.getRxPermissions(), mErrorHandler,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE);
    }

}
