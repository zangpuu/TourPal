package com.foot.tourpal.business.mine.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.foot.tourpal.business.mine.contract.MineContract;

import javax.inject.Inject;

/**
 * Created by ZhangPu on 2017/7/13.
 */
@FragmentScope
public class MineModel extends BaseModel implements MineContract.Model {

    @Inject
    public MineModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
