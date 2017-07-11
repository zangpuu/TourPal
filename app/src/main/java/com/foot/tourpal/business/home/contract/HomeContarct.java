package com.foot.tourpal.business.home.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by ZhangPu on 2017/7/11.
 */

public class HomeContarct {

    public interface View extends IView {
        //申请权限
        RxPermissions getRxPermissions();
    }

    public interface Model extends IModel {

    }

}
