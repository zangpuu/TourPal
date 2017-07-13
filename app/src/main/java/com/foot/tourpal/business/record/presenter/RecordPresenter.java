package com.foot.tourpal.business.record.presenter;

import android.app.Application;

import com.foot.tourpal.business.record.contract.RecordContract;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * Created by ZhangPu on 2017/7/13.
 */

public class RecordPresenter extends BasePresenter<RecordContract.Model, RecordContract.View> {

    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;

    @Inject
    public RecordPresenter(RecordContract.Model model, RecordContract.View view, RxErrorHandler handler,
                         AppManager appManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }
}
