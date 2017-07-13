package com.foot.tourpal.business.home.component;

import com.foot.tourpal.business.home.ui.HomeActivity;
import com.foot.tourpal.business.home.module.HomeModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by ZhangPu on 2017/7/11.
 */
@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}
