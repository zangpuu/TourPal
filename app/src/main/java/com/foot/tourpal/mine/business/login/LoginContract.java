package com.foot.tourpal.mine.business.login;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;


/**
 * Created by ZhangPu on 2017/7/6.
 */

public interface LoginContract {

    interface View extends IView {

    }

    interface Model extends IModel {
        Observable<GetCodeResponse> getCode(String phone);
    }
}
