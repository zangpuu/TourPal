package com.foot.tourpal.business.home.presenter;

import android.Manifest;
import android.app.Application;
import android.text.TextUtils;

import com.foot.tourpal.base.framework.AppCache;
import com.foot.tourpal.base.tool.RxUtils;
import com.foot.tourpal.business.home.contract.HomeContarct;
import com.foot.tourpal.business.home.model.entity.RefreshTokenResponse;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.PermissionUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

/**
 * Created by ZhangPu on 2017/7/11.
 */

public class HomePresenter extends BasePresenter<HomeContarct.Model, HomeContarct.View> {

    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;

    @Inject
    public HomePresenter(HomeContarct.Model model, HomeContarct.View rootView, RxErrorHandler handler,
                         AppManager appManager, Application application) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
        this.mApplication = application;
    }

    public void requestPermission() {
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

    public void tryLogin() {
        String userId = DataHelper.getStringSF(mApplication, AppCache.instance().KEY_USER_ID);
        String mobile = DataHelper.getStringSF(mApplication, AppCache.instance().KEY_MOBILE);
        String token = DataHelper.getStringSF(mApplication, AppCache.instance().KEY_TOKEN);
        if ((!TextUtils.isEmpty(userId)) &&
                (!TextUtils.isEmpty(mobile)) &&
                (!TextUtils.isEmpty(token))) {
            mModel.refreshToken(token, mobile, userId)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxUtils.bindToLifecycle((mRootView)))
                    .subscribe(new ErrorHandleSubscriber<RefreshTokenResponse>(mErrorHandler) {
                        @Override
                        public void onNext(@NonNull RefreshTokenResponse refreshTokenResponse) {
                            Timber.d(new Gson().toJson(refreshTokenResponse));
                            if (refreshTokenResponse.isSuccess()) {
                                AppCache.instance().setLogined(true);
                                DataHelper.setStringSF(mApplication, AppCache.instance().KEY_USER_ID, refreshTokenResponse.getData().getUserId());
                                DataHelper.setStringSF(mApplication, AppCache.instance().KEY_TOKEN, refreshTokenResponse.getData().getToken());
                            }
                            mRootView.showMessage(refreshTokenResponse.getMsg());
                        }
                    });
        }
    }

}
