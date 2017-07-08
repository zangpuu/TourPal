package com.foot.tourpal.mine.business.login;

import android.app.Application;

import com.foot.tourpal.base.tool.RxUtils;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

/**
 * Created by ZhangPu on 2017/7/6.
 */

public class LoginPresenter extends BasePresenter <LoginContract.Model, LoginContract.View> {

    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View view, RxErrorHandler handler,
                          AppManager appManager, Application application){
        super(model, view);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    public void requestCode(String phone){
        mModel.getCode(phone)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<GetCodeResponse>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetCodeResponse getCodeResponse) {
                        Timber.d(new Gson().toJson(getCodeResponse));
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
