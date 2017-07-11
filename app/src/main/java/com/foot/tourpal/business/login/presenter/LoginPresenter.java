package com.foot.tourpal.business.login.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.foot.tourpal.base.framework.AppCache;
import com.foot.tourpal.base.mvp.Api;
import com.foot.tourpal.base.tool.RxUtils;
import com.foot.tourpal.business.login.model.entity.GetCodeResponse;
import com.foot.tourpal.business.login.model.entity.LoginResponse;
import com.foot.tourpal.business.login.contract.LoginContract;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.UiUtils;

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
                        if(!TextUtils.isEmpty(getCodeResponse.getMsg())) {
                            UiUtils.snackbarText(getCodeResponse.getMsg());
                        }
                        if(getCodeResponse.getStatus() == Api.RequestSuccess) {
                            AppCache.instance().setUserId(getCodeResponse.getData().getUserId());
                        }
                    }
                });
    }

    public void login(String mobile, String code){
        if(TextUtils.isEmpty(mobile)) {
            mRootView.showMessage("请填写手机号");
            return;
        } else if (TextUtils.isEmpty(code)) {
            mRootView.showMessage("请填写验证码");
            return;
        } else if (TextUtils.isEmpty(AppCache.instance().getUserId())) {
            mRootView.showMessage("请重新获取验证码");
        }
        mModel.login(mobile, AppCache.instance().getUserId(), code)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.bindToLifecycle((mRootView)))
                .subscribe(new ErrorHandleSubscriber<LoginResponse>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {
                        Timber.d(new Gson().toJson(loginResponse));
                        if (loginResponse.getStatus() == Api.RequestSuccess) {
                            AppCache.instance().setToken(loginResponse.getData().getSysUserToken().getToken());
                        } else {
                            mRootView.showMessage(loginResponse.getMsg());
                        }
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
