package com.foot.tourpal.business.record.module;

import com.foot.tourpal.business.record.contract.RecordContract;
import com.foot.tourpal.business.record.model.RecordModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZhangPu on 2017/7/13.
 */
@Module
public class RecordModule {

    private RecordContract.View view;

    public RecordModule(RecordContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    RecordContract.View provideMineView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    RecordContract.Model provideMineModel(RecordModel model) {
        return model;
    }

}
