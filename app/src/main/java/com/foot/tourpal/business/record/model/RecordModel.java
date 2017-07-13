package com.foot.tourpal.business.record.model;

import com.foot.tourpal.business.record.contract.RecordContract;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * Created by ZhangPu on 2017/7/13.
 */
@FragmentScope
public class RecordModel extends BaseModel implements RecordContract.Model{

    @Inject
    public RecordModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
