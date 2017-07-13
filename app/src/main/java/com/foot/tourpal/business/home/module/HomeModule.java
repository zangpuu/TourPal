package com.foot.tourpal.business.home.module;

import com.foot.tourpal.business.home.contract.HomeContarct;
import com.foot.tourpal.business.home.model.HomeModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZhangPu on 2017/7/11.
 */
@Module
public class HomeModule {

    private HomeContarct.View view;

    public HomeModule(HomeContarct.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomeContarct.View provideHomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomeContarct.Model providerHomeModel(HomeModel model) {
        return model;
    }
}
