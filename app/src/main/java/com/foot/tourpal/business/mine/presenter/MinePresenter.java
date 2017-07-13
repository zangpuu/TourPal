package com.foot.tourpal.business.mine.presenter;

import android.app.Application;

import com.foot.tourpal.business.mine.contract.MineContract;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * Created by ZhangPu on 2017/7/13.
 */

public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {

    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;

    @Inject
    public MinePresenter(MineContract.Model model, MineContract.View view, RxErrorHandler handler,
                          AppManager appManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }
}
