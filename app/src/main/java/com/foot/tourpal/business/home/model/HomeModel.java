package com.foot.tourpal.business.home.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.foot.tourpal.business.home.contract.HomeContarct;

import javax.inject.Inject;

/**
 * Created by ZhangPu on 2017/7/11.
 */

@ActivityScope
public class HomeModel extends BaseModel implements HomeContarct.Model {
    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
