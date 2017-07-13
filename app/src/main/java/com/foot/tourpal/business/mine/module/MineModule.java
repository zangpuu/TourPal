package com.foot.tourpal.business.mine.module;

import com.foot.tourpal.business.mine.contract.MineContract;
import com.foot.tourpal.business.mine.model.MineModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZhangPu on 2017/7/13.
 */
@Module
public class MineModule {

    private MineContract.View view;

    public MineModule(MineContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    MineContract.View provideMineView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    MineContract.Model provideMineModel(MineModel model) {
        return model;
    }

}
